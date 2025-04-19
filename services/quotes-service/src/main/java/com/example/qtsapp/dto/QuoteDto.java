package com.example.qtsapp.dto;

import java.time.Instant;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class QuoteDto {
	private Long id;
	private String content;
	private String postedBy;
	private String author;
	private Instant postedOn;

}
