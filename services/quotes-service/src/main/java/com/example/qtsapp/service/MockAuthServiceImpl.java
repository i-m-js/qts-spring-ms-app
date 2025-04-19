package com.example.qtsapp.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.qtsapp.dto.AuthDto;
import com.example.qtsapp.dto.qtsuser.QuoteUserDto;
import com.example.qtsapp.entities.QuoteUser;
import com.example.qtsapp.exceptions.UserNotFoundException;
import com.example.qtsapp.repository.QuoteUserRepository;

import lombok.RequiredArgsConstructor;

@Service("mock-user-service")
@RequiredArgsConstructor
public class MockAuthServiceImpl implements AuthService {

	private final QuoteUserRepository quoteUserRepository;

	@Override
	public QuoteUserDto login(AuthDto authDto) {
		Optional<QuoteUser> userEntity = quoteUserRepository.findByUsername(authDto.username());
		QuoteUser user = userEntity
				.orElseThrow(() -> new UserNotFoundException("User not found for username:" + authDto.username()));
		return user.toDto();
	}

	@Override
	public QuoteUserDto signUp(AuthDto authDto) {
		throw new RuntimeException("Not implemented");
	}

}
