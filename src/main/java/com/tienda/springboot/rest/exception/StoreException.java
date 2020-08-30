package com.tienda.springboot.rest.exception;

public class StoreException extends RuntimeException {

	public StoreException(String exception) {
		super("StoreException: "+exception);
	}

}
