package com.example.qtsapp.dto.fiegn;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode
public class DummyJsonQuote {
	private Long id;
	private String quote;
	private String author;
}
