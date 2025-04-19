package com.example.qtsapp.entities;

import com.example.qtsapp.commons.QuoteEventType;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Entity
@Table(name = "quote_events")
public class QuoteEvent {
	@EmbeddedId
	private QuoteEventId event;
	private int eventType;

	void setEventType(QuoteEventType type) {
		eventType = type.getCode();
	}

	QuoteEventType getQuoteEventCode() {
		return QuoteEventType.fromCode(eventType);
	}

}
