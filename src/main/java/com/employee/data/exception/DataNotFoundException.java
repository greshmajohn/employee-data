package com.employee.data.exception;


public class DataNotFoundException extends Exception  {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String message;
	
	public DataNotFoundException(String message) {
		super(message);
	}

}
