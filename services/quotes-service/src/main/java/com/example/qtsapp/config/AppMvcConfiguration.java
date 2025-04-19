package com.example.qtsapp.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.qtsapp.interceptors.GenericInfoInterceptor;

import feign.Response;
import feign.codec.ErrorDecoder;
import lombok.RequiredArgsConstructor;

@Configuration
@RequiredArgsConstructor
public class AppMvcConfiguration implements WebMvcConfigurer {
	private final Logger log = LoggerFactory.getLogger(AppMvcConfiguration.class);
	private final GenericInfoInterceptor genericInfoInterceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(genericInfoInterceptor).addPathPatterns("/**");
	}


}
