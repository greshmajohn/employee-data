package com.learn.ms.insurance.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionDto {
	
	String title;
	String exceptionMessage;
	String status;
}
