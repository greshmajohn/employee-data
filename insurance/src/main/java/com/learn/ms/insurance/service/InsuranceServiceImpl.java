package com.learn.ms.insurance.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.learn.ms.insurance.dao.InsuranceDto;
import com.learn.ms.insurance.dao.ResponseDto;
import com.learn.ms.insurance.entity.InsuranceEntity;
import com.learn.ms.insurance.repository.InsuranceRepository;

import jakarta.transaction.Transactional;

@Service
public class InsuranceServiceImpl implements InsuranceService {
	
	@Autowired
	InsuranceRepository insuranceRepo;

	@Override
	@Transactional
	public ResponseDto saveInsurance(InsuranceDto insuranceDto) {
		System.out.println("inside this method");
		InsuranceEntity insurance=new InsuranceEntity();
		insurance.setInsuranceName(insuranceDto.getInsuranceName());
		insurance.setMaxInsurance(insuranceDto.getMaxInsuranceDependents());
		insurance.setInsuranceAmount(insuranceDto.getInsuranceAmount());
		insurance.setValidFrom(insuranceDto.getValidFrom());
		insurance.setValidTo(insuranceDto.getValidTo());
		insurance.setCreatedOn(LocalDateTime.now());
		insurance.setCreatedBy("SYSTEM");
		insuranceRepo.save(insurance);
		ResponseDto resp=new ResponseDto("Success","Insurance Data inserted successfully");
		return resp;
	}

	@Override
	public List<InsuranceDto> getAllInsurance(Long id) {
		List<InsuranceDto> resultList=new ArrayList<InsuranceDto>();
		if(id==null) {
			setAllInsuranceList(resultList,insuranceRepo.findAll());
		}else {
			Optional<InsuranceEntity> insuranceOpt=insuranceRepo.findById(id);
			if(insuranceOpt.isPresent()) {
				setInsuranceData(resultList,insuranceOpt.get());
				
			}
				
			
			
		}
		return resultList;
		
	}

	private void setAllInsuranceList(List<InsuranceDto> resultList, List<InsuranceEntity> insuranceList) {
		for(InsuranceEntity insuranceObj:insuranceList) {
			setInsuranceData(resultList,insuranceObj);
		}
		
	}

	private void setInsuranceData(List<InsuranceDto> resultList, InsuranceEntity insuranceEntity) {
		InsuranceDto insuranceResp=new InsuranceDto();
		insuranceResp.setInsuranceAmount(insuranceEntity.getInsuranceAmount());
		insuranceResp.setInsuranceName(insuranceEntity.getInsuranceName());
		insuranceResp.setMaxInsuranceDependents(insuranceEntity.getMaxInsurance());
		insuranceResp.setValidFrom(insuranceEntity.getValidFrom());
		insuranceResp.setValidTo(insuranceEntity.getValidTo());
		resultList.add(insuranceResp);
	}

}
