package com.autoparkingwithdb.Exceptions;

@SuppressWarnings("serial")
public class InvalidCredentialsException extends Exception {
  
	public InvalidCredentialsException(String exceptionName) {
		super(exceptionName);
	}
}
