package com.cep.apicep.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErroConsultaViaCepException extends RuntimeException{

	private static final long serialVersionUID = 1L;

	public ErroConsultaViaCepException(String msg) {
		super(msg);
	}
	
}
