package com.autoparkingwithui.Exceptions;

@SuppressWarnings("serial")
public class IncorrectPlateFormatException extends Exception {
  
	public IncorrectPlateFormatException(String exceptionName) {
		super(exceptionName);
	}
}
