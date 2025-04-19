package com.example.qtsapp.exceptions;

public class ActionNotAllowed extends RuntimeException {
	private static final long serialVersionUID = 1L;

	public ActionNotAllowed(String message) {
		super(message);
	}
	

}
