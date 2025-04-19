package com.example.qtsapp.service;

import java.util.List;

import com.example.qtsapp.dto.QuoteDto;

public interface QuoteService {
	long getAvailableQuoteCount();

	QuoteDto createQuote(QuoteDto dto);

	QuoteDto getQuote(long qId);

	void likeQuote(long qId);

	void removeQuoteLike(long qId);

	void dislikeQuote(long qId);

	void removeQuoteDislike(long qId);

	void createQuotes(List<QuoteDto> quoteDtos);
}
