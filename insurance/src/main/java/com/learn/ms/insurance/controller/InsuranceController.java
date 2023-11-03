package com.learn.ms.insurance.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.learn.ms.insurance.dao.InsuranceDto;
import com.learn.ms.insurance.dao.ResponseDto;
import com.learn.ms.insurance.dao.ResponseObjectDto;
import com.learn.ms.insurance.entity.InsuranceEntity;
import com.learn.ms.insurance.service.InsuranceService;
/*
 * author @greshma.john.
 * controller API to do the CRUD operations of insurance details of employees
 */
import com.learn.ms.insurance.service.aop.LogExecutionTime;

import jakarta.validation.Valid;
import lombok.extern.log4j.Log4j2;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("insurance")
@Slf4j
public class InsuranceController {
	
	
//	@Autowired
	private InsuranceService insuranceSerice;
	
	public InsuranceController(InsuranceService insuranceSerice ) {
		this.insuranceSerice=insuranceSerice;
	}
	
	/*
	 * get all insurance details
	 */
	@GetMapping("fetch-insurance")
	@LogExecutionTime
	public ResponseEntity<ResponseObjectDto<List<InsuranceDto>>> getAllInsurance(@RequestParam(required=false) Long id){
		ResponseObjectDto<List<InsuranceDto>> response=new ResponseObjectDto<>();

		ResponseDto resDto=new ResponseDto("Success","Insurance details fetched successfully !");
		List<InsuranceDto> insList=insuranceSerice.getAllInsurance(id);
		if(!(insList==null||insList.isEmpty())) {
			response.setResponseObject(insList);
			response.setResponseStatus(resDto);
		
		}
				
		else {
			resDto.setResponseMessage("Data not found for the given input");
			resDto.setResponseStatus("Failed");
			response.setResponseStatus(resDto);
			
		}
		return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);	
	}
	
	@PostMapping("insurance")
	@LogExecutionTime
	public ResponseEntity<ResponseDto> saveInsuranceData(@RequestBody @Valid InsuranceDto insuranceObj){
		
		return new ResponseEntity<>(insuranceSerice.saveInsurance(insuranceObj),HttpStatus.OK);
	}
	

}
