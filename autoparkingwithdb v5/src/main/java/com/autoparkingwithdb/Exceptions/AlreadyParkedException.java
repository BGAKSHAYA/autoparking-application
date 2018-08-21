package com.autoparkingwithdb.Exceptions;

@SuppressWarnings("serial")
public class AlreadyParkedException extends Exception {
  
	public AlreadyParkedException(String exceptionName) {
		super(exceptionName);
	}
}
