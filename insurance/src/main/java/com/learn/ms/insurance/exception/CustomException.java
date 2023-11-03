package com.learn.ms.insurance.exception;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;


@ControllerAdvice
public class CustomException {
	
	@ExceptionHandler({MethodArgumentNotValidException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionDto> handleMethodArgumentException(MethodArgumentNotValidException e){
		ExceptionDto exception=new ExceptionDto();
		exception.setExceptionMessage(e.getMessage());
		exception.setTitle("Invalid input");
		exception.setStatus(HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler({ConstraintViolationException.class})
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	public ResponseEntity<ExceptionDto> handleException(ConstraintViolationException e){
		
		ExceptionDto exception=new ExceptionDto();

		exception.setExceptionMessage(e.getErrorMessage().split("Detail:")[1].replace("]", ""));
		exception.setTitle("Constraint Violation Exception");
		exception.setStatus(HttpStatus.BAD_REQUEST.name());
		
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	

}
