package com.cep.apicep.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidPathVariableException extends RuntimeException {

	private static final long serialVersionUID = 1L;
	
	
	public InvalidPathVariableException(String msg) {
		super(msg);
	}
}
