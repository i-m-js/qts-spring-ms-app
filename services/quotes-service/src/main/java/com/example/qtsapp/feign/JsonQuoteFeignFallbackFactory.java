package com.example.qtsapp.feign;

import java.util.Collections;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FallbackFactory;
import org.springframework.stereotype.Component;

import com.example.qtsapp.dto.DummmyJsonQuoteListDto;

@Component
public class JsonQuoteFeignFallbackFactory implements FallbackFactory<JsonQuoteFeignClient> {

	@Override
	public JsonQuoteFeignClient create(Throwable cause) {

		return new JsonQuoteFeignClient() {
			private final Logger log = LoggerFactory.getLogger(JsonQuoteFeignClient.class);

			@Override
			public DummmyJsonQuoteListDto getQuotes(int limit) {
				log.error("From feign fallback factory", cause);
				return new DummmyJsonQuoteListDto(Collections.emptyList());
			}
		};
	}

}
