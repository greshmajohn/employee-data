package com.employee.data.exception;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class EmployeeException {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ExceptionDto> handleMethodArgumentInvalidException(MethodArgumentNotValidException e)
	{
		List<String> errors = e.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
		ExceptionDto exception=new ExceptionDto("Invalid Input",errors,HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(IllegalArgumentException.class)
	public ResponseEntity<ExceptionDto> handleIllegalArumentException(IllegalArgumentException e){
		List<String> errors=new ArrayList<String>();
		errors.add(e.getMessage());
		ExceptionDto exception=new ExceptionDto("Invalid Input",errors,HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(DataNotFoundException.class)
	public ResponseEntity<ExceptionDto> handleDataNotFoundException(DataNotFoundException e){
		System.out.println("coming inside "+e.getMessage());
		List<String> errors=new ArrayList<String>();
		errors.add(e.getMessage());
		ExceptionDto exception=new ExceptionDto("Data Not Found",errors,HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionDto> handleHttpMsgNotReadableException(HttpMessageNotReadableException e){
		List<String> errors=new ArrayList<String>();
		errors.add(e.getMessage());
		ExceptionDto exception=new ExceptionDto("Syntax Error",errors,HttpStatus.BAD_REQUEST.name());
		return new ResponseEntity<>(exception,HttpStatus.BAD_REQUEST);
	}
}
