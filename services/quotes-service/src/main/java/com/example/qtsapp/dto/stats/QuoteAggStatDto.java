package com.example.qtsapp.dto.stats;

public record QuoteAggStatDto(Long likes, Long dislikes) {
	public QuoteAggStatDto() {
		this(0L, 0L);
	}
}
