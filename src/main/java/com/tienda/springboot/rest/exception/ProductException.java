package com.tienda.springboot.rest.exception;

public class ProductException extends RuntimeException {

	public ProductException(String exception) {
		super("ProductException: "+exception);
	}

}
