package com.autoparkingwithui.Exceptions;

@SuppressWarnings("serial")
public class EmptyAvailableSlotsException extends Exception {
  
	public EmptyAvailableSlotsException(String exceptionName) {
		super(exceptionName);
	}
}
