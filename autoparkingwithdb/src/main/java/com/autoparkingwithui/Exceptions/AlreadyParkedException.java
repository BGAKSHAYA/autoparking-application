package com.autoparkingwithui.Exceptions;

@SuppressWarnings("serial")
public class AlreadyParkedException extends Exception {
  
	public AlreadyParkedException(String exceptionName) {
		super(exceptionName);
	}
}
