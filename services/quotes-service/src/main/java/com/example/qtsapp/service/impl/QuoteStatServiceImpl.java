package com.example.qtsapp.service.impl;

import org.springframework.stereotype.Service;

import com.example.qtsapp.commons.AppContext;
import com.example.qtsapp.dto.stats.QuoteAggStatDto;
import com.example.qtsapp.dto.stats.QuoteUserStat;
import com.example.qtsapp.repository.QuoteEventRepository;
import com.example.qtsapp.service.QuoteStatService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuoteStatServiceImpl implements QuoteStatService {
	private final QuoteEventRepository quoteEventRepository;

	// FIXME: before making this cacheable, add the user id part of arguments
	@Override
	public QuoteAggStatDto getQuoteStats(long quoteId) {
		QuoteAggStatDto aggStatDto = quoteEventRepository.loadLikeDislikeStatus(quoteId)
				.orElseGet(() -> new QuoteAggStatDto());

		return aggStatDto;
	}

	@Override
	public QuoteUserStat getUserQuoteStat(long quoteId) {
		QuoteAggStatDto userLikeStat = quoteEventRepository.loadUserLikeDislikeStatus(quoteId, AppContext.getUserId())
				.orElseGet(() -> new QuoteAggStatDto());
		return new QuoteUserStat(userLikeStat.likes() != 0, userLikeStat.dislikes() != 0);
	}

}
