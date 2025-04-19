package com.example.qtsapp.feign;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.qtsapp.TrailItem;
import com.example.qtsapp.dto.DummmyJsonQuoteListDto;

@TrailItem
//@Component
public class JsonQuoteFeignFallback implements JsonQuoteFeignClient {

	private final Logger log = LoggerFactory.getLogger(JsonQuoteFeignFallback.class);

	@Override
	public DummmyJsonQuoteListDto getQuotes(int limit) {
		log.error("fallback return executed:: {}", limit);
		return new DummmyJsonQuoteListDto(Collections.emptyList());
	}

}

