package com.example.qtsapp.entities;

import java.time.Instant;

import com.example.qtsapp.dto.QuoteDto;
import com.example.qtsapp.dto.QuoteDto.QuoteDtoBuilder;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Entity
@Table(name = "quotes")
public class Quote {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String content;

	@ManyToOne
	private QuoteUser posted_by;

	private String author;

	private Instant posted_on;
	private Instant modified_on;

	public QuoteDto toDto() {
		QuoteDtoBuilder builder = QuoteDto.builder();
		builder.id(id)//
				.content(content)//
				.author(author)//
				.postedOn(posted_on);

		if (posted_by != null) {
			builder.postedBy(posted_by.getUsername());
		}
		return builder.build();
	}

	public static Quote fromDto(QuoteDto dto, QuoteUser postedBy) {
		return Quote.builder()//
				.id(dto.getId())//
				.content(dto.getContent())//
				.posted_by(postedBy)//
				.author(dto.getAuthor())//
				.posted_on(dto.getPostedOn())//
				.build();
	}

}
