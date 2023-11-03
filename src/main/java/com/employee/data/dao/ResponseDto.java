package com.employee.data.dao;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseDto {
	
	String ResponseStatus;
	String ResponseMessage;
	

}
