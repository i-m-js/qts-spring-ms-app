package com.example.qtsapp.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.qtsapp.commons.AppContext;
import com.example.qtsapp.commons.QuoteEventType;
import com.example.qtsapp.commons.utils.AppConstants;
import com.example.qtsapp.dto.QuoteDto;
import com.example.qtsapp.entities.Quote;
import com.example.qtsapp.entities.QuoteUser;
import com.example.qtsapp.exceptions.ActionNotAllowed;
import com.example.qtsapp.exceptions.ItemNotFoundException;
import com.example.qtsapp.exceptions.UserNotFoundException;
import com.example.qtsapp.repository.QuoteEventRepository;
import com.example.qtsapp.repository.QuoteRepository;
import com.example.qtsapp.repository.QuoteUserRepository;
import com.example.qtsapp.service.QuoteService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuoteServiceImpl implements QuoteService {

	private final Logger log = LoggerFactory.getLogger(QuoteServiceImpl.class);

	private final QuoteRepository quoteRepository;
	private final QuoteEventRepository quoteEventRepository;
	private final QuoteUserRepository quoteUserRepository;
	private final JdbcTemplate jdbcTemplate;

	@Override
	@Transactional
	public QuoteDto createQuote(QuoteDto dto) {
		QuoteUser user = getQuoteUser();

		log.debug("Saving quote dto: {}", dto);
		Quote quote = Quote.fromDto(dto, user);
		Quote saved = quoteRepository.save(quote);

		return saved.toDto();
	}

	@Override
	@Transactional
	public void createQuotes(List<QuoteDto> quoteDtos) {
		QuoteUser user = getQuoteUser();
		log.debug("Saving {} quotes", quoteDtos.size());
		List<Quote> list = quoteDtos.stream().map(q -> Quote.fromDto(q, user)).toList();

//		jdbcTemplate.batchUpdate("INSERT INTO QUOTES (content, posted_by_id, author) VALUES (? ,? ,?)", (ps, i) -> {
//		});
		log.info("Inserting items via batch");
		jdbcTemplate.batchUpdate("INSERT INTO QUOTES (content, posted_by_id, author) VALUES (?,?,?)", list, 50,
				(ps, quote) -> {
					ps.setString(1, quote.getContent());
					ps.setLong(2, user.getId());
					ps.setString(3, quote.getAuthor());
				});
		log.info("Batch insert completed.!");
//		quoteRepository.saveAll(list);
	}

	private QuoteUser getQuoteUser() {
		Long userId = AppContext.getUserId();
		QuoteUser user = quoteUserRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("Unable to find the user for id " + userId));
		return user;
	}

	@Override
	@Transactional(readOnly = true)
	public long getAvailableQuoteCount() {
		return quoteRepository.count();
	}

	@Override
	@Transactional(readOnly = true)
	public QuoteDto getQuote(long qId) {
		return quoteRepository.findById(qId)
				.orElseThrow(() -> new ItemNotFoundException("Quote with id " + qId + " not found")).toDto();
	}

	@Override
	public void likeQuote(long qId) {
		insertLikeDislikeEvent(qId, QuoteEventType.LIKE);
	}

	@Override
	public void removeQuoteLike(long qId) {
		removeQuoteEvent(qId, QuoteEventType.LIKE);
	}

	@Override
	public void dislikeQuote(long qId) {
		insertLikeDislikeEvent(qId, QuoteEventType.DISLIKE);
	}

	@Override
	public void removeQuoteDislike(long qId) {
		removeQuoteEvent(qId, QuoteEventType.DISLIKE);
	}

	@Transactional
	private final void removeQuoteEvent(Long qId, QuoteEventType targetEvent) {
		Long uId = getUserId();
		quoteEventRepository.removeUserQuoteEvent(qId, uId, targetEvent.getCode());
	}

	@Transactional
	private final void insertLikeDislikeEvent(long qId, QuoteEventType targetState) {
		Long uId = getUserId();
		quoteEventRepository.removeUserQuoteEvent(qId, uId,
				targetState == QuoteEventType.DISLIKE ? QuoteEventType.LIKE.getCode()
						: QuoteEventType.DISLIKE.getCode());
		quoteEventRepository.insertUserQuoteEvent(qId, uId, targetState.getCode());
	}

	private final Long getUserId() {
		if (AppContext.getParam(AppConstants.X_USER_ID, Long.class).isPresent()) {
			return AppContext.getParam(AppConstants.X_USER_ID, Long.class).get();
		} else {
			throw new ActionNotAllowed("Used id not found");
		}
	}

}
