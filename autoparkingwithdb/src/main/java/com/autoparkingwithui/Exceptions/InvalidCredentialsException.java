package com.autoparkingwithui.Exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends Exception {
  
	public InvalidCredentialsException(String exceptionName) {
		super(exceptionName);
	}
}
