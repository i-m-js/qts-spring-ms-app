package com.example.qtsapp.dev;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.example.qtsapp.commons.AppContext;
import com.example.qtsapp.commons.utils.AppConstants;
import com.example.qtsapp.dto.DummmyJsonQuoteListDto;
import com.example.qtsapp.dto.QuoteDto;
import com.example.qtsapp.feign.JsonQuoteFeignClient;
import com.example.qtsapp.service.QuoteService;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Configuration
public class AppDataLoader {
	@Value("${config.value.flag:WTF}")
	String key;

	private final QuoteService quoteService;

	private final JsonQuoteFeignClient jsonQuoteFeignClient;

	@PostConstruct
	void postConstruct() {
		quoteService.getAvailableQuoteCount();
		DummmyJsonQuoteListDto quotes = jsonQuoteFeignClient.getQuotes(1000);
		System.err.println("============================");
		System.err.println(quotes.quotes().size());
		System.err.println("============================");
		List<QuoteDto> quoteDtos = quotes.quotes().stream().map(quote -> {
			return new QuoteDto(null, quote.getQuote(), "admin", quote.getAuthor(), null);
		}).collect(Collectors.toList());
		AppContext.putParam(AppConstants.X_USER_ID, 1L);
		this.quoteService.createQuotes(quoteDtos);

	}
}
