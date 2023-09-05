package com.myapp.employee.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice

public class EmployeeExceptions {
	@ExceptionHandler(NameNotFoundException.class)
	public ResponseEntity<Object> nameNotFoundHandling(NameNotFoundException n){
		return new ResponseEntity<> (n.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AgeNotEligibleException.class)
	public ResponseEntity<Object> AgeNotEligibleHandling(AgeNotEligibleException k){
		return new ResponseEntity<>(k.getMessage(),HttpStatus.NOT_FOUND);
	}
	
	@ExceptionHandler(AgeNotFoundListException.class)
	public ResponseEntity<Object> AgeNotFoundListException(AgeNotFoundListException l){
		return new ResponseEntity<>(l.getMessage(),HttpStatus.NOT_FOUND);
	}

}
