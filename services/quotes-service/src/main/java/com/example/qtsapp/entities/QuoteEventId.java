package com.example.qtsapp.entities;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@EqualsAndHashCode
public class QuoteEventId implements Serializable {
	private static final long serialVersionUID = 1L;
	@ManyToOne
	private QuoteUser user;

	@ManyToOne
	private Quote quote;
}
