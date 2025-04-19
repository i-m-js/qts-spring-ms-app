package com.example.qtsapp.dto.qtsuser;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@JsonInclude(value = Include.NON_NULL)
public class QuoteUserDto {
	private Long id;
	private String username;
	private String email;
	@ToString.Exclude
	private String password;
	private String userSource;

	@JsonIgnore
	private Instant createdOn;

	public String getPassword() {
		return null;
	}
}
