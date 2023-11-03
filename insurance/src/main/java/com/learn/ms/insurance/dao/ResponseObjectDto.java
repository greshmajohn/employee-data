package com.learn.ms.insurance.dao;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseObjectDto<T> {
	
	private ResponseDto responseStatus;
	private T responseObject;
	

}
