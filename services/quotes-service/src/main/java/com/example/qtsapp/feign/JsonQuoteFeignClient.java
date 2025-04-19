package com.example.qtsapp.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.qtsapp.dto.DummmyJsonQuoteListDto;

@FeignClient(name = "dummyjson-quotes-client", url = "https://dummyjson.com", //
		fallbackFactory = JsonQuoteFeignFallbackFactory.class)
public interface JsonQuoteFeignClient {
	@GetMapping("/quotes")
	DummmyJsonQuoteListDto getQuotes(@RequestParam("limit") int limit);
}
