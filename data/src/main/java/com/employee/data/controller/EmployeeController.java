package com.employee.data.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.employee.data.dao.EmployeeRequestDao;
import com.employee.data.dao.EmployeeResponseDao;
import com.employee.data.dao.ResponseDto;
import com.employee.data.dao.ResponseObjectDto;
import com.employee.data.service.EmployeeService;


import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
	
	EmployeeService empService;
	
	public EmployeeController(EmployeeService empService) {
		this.empService=empService;
	}
	
	@PostMapping("/save-employee")
	public ResponseEntity<ResponseDto> saveEmployeeDeatils(@RequestBody @Valid EmployeeRequestDao employeeDao){
		System.out.println("save employee");
		return new ResponseEntity<ResponseDto>(empService.saveEmployeeDetails(employeeDao),HttpStatus.OK);
	}
	
	/*
	 * fetch all employee details
	 */
	@GetMapping("/fetch-employees")
	public ResponseEntity<ResponseObjectDto<EmployeeResponseDao>> getEmployeeDetails(){
		List<EmployeeResponseDao> empList=empService.getEmployeeDetails();
		
		ResponseDto respObj=new ResponseDto("Failed","Failed to Fetch employee data");
		if(!empList.isEmpty())
			respObj=new ResponseDto("Success","Employee Details fetched successfully !");
		
		ResponseObjectDto<EmployeeResponseDao> response=new ResponseObjectDto(respObj,empList);
		if(respObj.getResponseStatus().equals("Success"))
			return new ResponseEntity<ResponseObjectDto<EmployeeResponseDao>>(response,HttpStatus.OK);
		else
			return new ResponseEntity<ResponseObjectDto<EmployeeResponseDao>>(response,HttpStatus.BAD_REQUEST);
	}
	
	

}
