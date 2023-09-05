package com.myapp.employee.exceptions;

@SuppressWarnings("serial")
public class AgeNotEligibleException extends Exception {
	public AgeNotEligibleException(String msg) {
		super(msg);
	}
}

