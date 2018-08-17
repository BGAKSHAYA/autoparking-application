package com.autoparkingwithdb.Exceptions;

@SuppressWarnings("serial")
public class IncorrectPlateFormatException extends Exception {
  
	public IncorrectPlateFormatException(String exceptionName) {
		super(exceptionName);
	}
}
