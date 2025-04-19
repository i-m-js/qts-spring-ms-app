package com.example.qtsapp.commons;

import java.util.Arrays;
import java.util.Optional;

public enum QuotesUserType {
	ANNONYMOUS(0), BASIC(1); // OAUTH_GOOGLE, OAUTH_FACEBOOK, OAUTH_GITHUB, OAUTH_OTHER

	private final int code;

	QuotesUserType(int code) {
		this.code = code;
	}

	public int getCode() {
		return this.code;
	}

	public static QuotesUserType getUserType(int code) {
		Optional<QuotesUserType> found = Arrays.stream(QuotesUserType.values()).filter(q -> q.getCode() == code)
				.findFirst();

		return found.orElseThrow(() -> new IllegalArgumentException("Invalid user type"));
	}
}
