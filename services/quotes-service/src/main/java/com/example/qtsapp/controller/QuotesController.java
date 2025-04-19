package com.example.qtsapp.controller;

import java.util.concurrent.ThreadLocalRandom;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.qtsapp.commons.AppContext;
import com.example.qtsapp.dto.QuoteDto;
import com.example.qtsapp.dto.stats.QuoteAggStatDto;
import com.example.qtsapp.dto.stats.QuoteStatDto;
import com.example.qtsapp.dto.stats.QuoteUserStat;
import com.example.qtsapp.feign.JsonQuoteFeignClient;
import com.example.qtsapp.service.QuoteService;
import com.example.qtsapp.service.QuoteStatService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/quote")
@RequiredArgsConstructor
public class QuotesController {

	private final QuoteService quoteService;
	private final QuoteStatService quoteStatService;
	private final JsonQuoteFeignClient jsonQuoteFeignClient;
	private final ThreadLocalRandom rand = ThreadLocalRandom.current();

//	private final JsonQuoteFeignClient jsonQuoteFeignClient;

	@PostConstruct
	void postCont() {
//		List<DummyJsonQuote> res = jsonQuoteFeignClient.getQuotes();
//		System.err.println(res);
//		RestClient restClient = RestClient.create();
//		Object res = restClient.get().uri("https://dummyjson.com/quotes").retrieve().body(Object.class);
//		System.err.println(res.toString());

	}

	@GetMapping("/loadMock")
	void loadMock() {
//		quoteService.getAvailableQuoteCount();
//		DummmyJsonQuoteListDto quotes = jsonQuoteFeignClient.getQuotes(1000);
//		System.err.println("============================");
//		System.err.println(quotes.quotes().size());
//		System.err.println("============================");
//		List<QuoteDto> quoteDtos = quotes.quotes().stream().map(quote -> {
//			return new QuoteDto(null, quote.getQuote(), "admin", quote.getAuthor(), null);
//		}).collect(Collectors.toList());
//		AppContext.putParam(AppConstants.X_USER_ID, 1L);
//		this.quoteService.createQuotes(quoteDtos);
	}

	@PostMapping("")
	QuoteDto createQuote(QuoteDto dto) {
		return quoteService.createQuote(dto);
	}

	@GetMapping("/random")
	QuoteDto getRandomQuote() {
		long id = rand.nextLong(1, quoteService.getAvailableQuoteCount() + 1);
		return quoteService.getQuote(id);
	}

	@GetMapping("/{id}")
	QuoteDto getQuote(@PathVariable Long id) {
		return quoteService.getQuote(id);
	}

	@GetMapping("/{id}/stats")
	QuoteStatDto getQuoteStats(@PathVariable long id) {
		QuoteAggStatDto quoteStats = quoteStatService.getQuoteStats(id);
		QuoteUserStat quoteUserStat = null;
		if (AppContext.getUserId() != null) {
			quoteUserStat = quoteStatService.getUserQuoteStat(id);
		}
		return new QuoteStatDto(id, quoteStats, quoteUserStat);
	}

	@PostMapping("/{id}/like")
	void likeQuote(@PathVariable Long id) {
		quoteService.likeQuote(id);
	}

	@PostMapping("/{id}/dislike")
	void dislikeQuote(@PathVariable Long id) {
		quoteService.dislikeQuote(id);
	}

	@DeleteMapping("/{id}/like")
	void removeQuoteLike(@PathVariable Long id) {
		quoteService.removeQuoteLike(id);
	}

	@DeleteMapping("/{id}/dislike")
	void removeQuoteDislike(@PathVariable Long id) {
		quoteService.removeQuoteDislike(id);
	}

}
