package com.autoparkingwithdb.Exceptions;

@SuppressWarnings("serial")
public class NoSuchVehicleException extends Exception {
  
	public NoSuchVehicleException(String exceptionName) {
		super(exceptionName);
	}
}
