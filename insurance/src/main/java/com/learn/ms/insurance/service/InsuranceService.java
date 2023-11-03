package com.learn.ms.insurance.service;

import java.util.List;

import com.learn.ms.insurance.dao.InsuranceDto;
import com.learn.ms.insurance.dao.ResponseDto;
import com.learn.ms.insurance.entity.InsuranceEntity;

public interface InsuranceService {
	
	public ResponseDto saveInsurance(InsuranceDto insuranceDto);
	
	public List<InsuranceDto> getAllInsurance(Long id);
	

}
