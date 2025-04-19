package com.example.qtsapp.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.example.qtsapp.commons.utils.AppConstants;
import com.example.qtsapp.dto.AuthDto;
import com.example.qtsapp.dto.qtsuser.QuoteUserDto;
import com.example.qtsapp.service.AuthService;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/login")
	@ResponseStatus(code = HttpStatus.OK)
	ResponseEntity<QuoteUserDto> login(@RequestBody AuthDto auth, HttpServletResponse response) {
		QuoteUserDto login = authService.login(auth);
		response.addHeader(AppConstants.X_USER_ID, String.valueOf(login.getId()));
		return ResponseEntity.ok().body(login);
	}

	@GetMapping("/login")
	Map<String, String> loginGet() {
		return Map.of("STatus", "Succ");
	}

}
