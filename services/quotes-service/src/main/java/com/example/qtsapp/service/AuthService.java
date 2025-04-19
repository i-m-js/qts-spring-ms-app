package com.example.qtsapp.service;

import com.example.qtsapp.dto.AuthDto;
import com.example.qtsapp.dto.qtsuser.QuoteUserDto;

public interface AuthService {
	QuoteUserDto login(AuthDto authDto);

	QuoteUserDto signUp(AuthDto authDto);
}
