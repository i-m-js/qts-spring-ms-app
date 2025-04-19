package com.example.qtsapp.service;

import com.example.qtsapp.dto.stats.QuoteAggStatDto;
import com.example.qtsapp.dto.stats.QuoteUserStat;

public interface QuoteStatService {
	QuoteAggStatDto getQuoteStats(long quoteId);

	QuoteUserStat getUserQuoteStat(long quoteId);
}
