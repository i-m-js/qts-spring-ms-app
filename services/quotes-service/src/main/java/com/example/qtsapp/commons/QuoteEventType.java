package com.example.qtsapp.commons;

import java.util.Arrays;
import java.util.Optional;

public enum QuoteEventType {
	LIKE(0), DISLIKE(1);

	private final int code;

	private QuoteEventType(int code) {
		this.code = code;
	}

	public int getCode() {
		return code;
	}

	public static QuoteEventType fromCode(int code) {

		Optional<QuoteEventType> found = Arrays.stream(QuoteEventType.values()).filter(e -> e.getCode() == code)
				.findFirst();
		return found.orElseThrow(() -> new IllegalArgumentException("provided code is not supported"));
	}

}
