package com.lasbleiso.exceptions;

public class ApiKeyInvalidException extends Exception{

	public ApiKeyInvalidException() {
		super();
	}
	
	public ApiKeyInvalidException(String message) {
		super(message);
	}
}
