package com.autoparkingwithui.Exceptions;

@SuppressWarnings("serial")
public class NoSuchVehicleException extends Exception {
  
	public NoSuchVehicleException(String exceptionName) {
		super(exceptionName);
	}
}
