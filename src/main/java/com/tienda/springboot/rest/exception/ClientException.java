package com.tienda.springboot.rest.exception;

public class ClientException extends RuntimeException {

	public ClientException(String exception) {
		super("ClientException: "+exception);
	}

}
