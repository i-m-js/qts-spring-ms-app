package com.example.qtsapp.entities;

import java.time.Instant;

import com.example.qtsapp.commons.QuotesUserType;
import com.example.qtsapp.dto.qtsuser.QuoteUserDto;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "quote_users")
public class QuoteUser {
	@Id
	private Long id;
	private String username;
	private String email;
	@Builder.Default
	@ToString.Exclude
	private String password = null;
	private int userSource;

	private Instant createdOn;

	void setUserSource(QuotesUserType userType) {
		this.userSource = userType.getCode();
	}

	public QuoteUserDto toDto() {
		return QuoteUserDto.builder()//
				.id(id).username(username).email(email)//
				.build();
	}

}
