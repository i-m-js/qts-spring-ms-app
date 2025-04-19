package com.example.qtsapp.feign;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import feign.Response;
import feign.codec.ErrorDecoder;

@Component
public class FeignErrorDecoder implements ErrorDecoder {
	private final ErrorDecoder decoder = new ErrorDecoder.Default();
	private final Logger log = LoggerFactory.getLogger(FeignErrorDecoder.class);

	@Override
	public Exception decode(String methodKey, Response response) {
		log.error("Feign Failure {}", response.request().url());
		return decoder.decode(methodKey, response);
	}

}
