package com.learn.ms.insurance.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.learn.ms.insurance.dao.InsuranceDto;
import com.learn.ms.insurance.dao.ResponseDto;
import com.learn.ms.insurance.entity.InsuranceEntity;
import com.learn.ms.insurance.repository.InsuranceRepository;

@SpringBootTest
public class InsuranceServiceImplTest {
	
	@InjectMocks
	InsuranceServiceImpl service;
	
	@MockBean
	InsuranceRepository insuRepo;
	
	@BeforeEach
	public void init() {
	    MockitoAnnotations.openMocks(this);
	}
	
	@Test
	void getAllInsurance() {
		List<InsuranceEntity> insuranceList=getInsuranceList();
		Mockito.when(insuRepo.findAll()).thenReturn(insuranceList);
		List<InsuranceDto> insuranceResult=service.getAllInsurance(null);
		Assertions.assertTrue(insuranceResult.size()>0);
	}
	@Test
	void getAllInsuranceWithId() {
		Mockito.when(insuRepo.findById(1L)).thenReturn(getOptionalObj());
		List<InsuranceDto> insuranceResult=service.getAllInsurance(1L);
		Assertions.assertEquals(1,insuranceResult.size());
		
	}
	
	@Test
	void saveInsurance() {
		InsuranceDto insuranceDto=new InsuranceDto();
		insuranceDto.setInsuranceName("Test");
		insuranceDto.setMaxInsuranceDependents(3);
		insuranceDto.setInsuranceAmount(new BigDecimal(2000.0));
		insuranceDto.setValidFrom(LocalDate.now().minusYears(3));
		insuranceDto.setValidTo(LocalDate.now().plusYears(1));
		ResponseDto saveResult=service.saveInsurance(insuranceDto);
		Assertions.assertEquals(saveResult.getResponseStatus(), "Success");
	}

	private Optional<InsuranceEntity> getOptionalObj() {
		return Optional.of(getInsuranceObj());
	}

	private List<InsuranceEntity> getInsuranceList() {
		List<InsuranceEntity> insuranceList=new ArrayList<InsuranceEntity>();
		InsuranceEntity insuranceObj=getInsuranceObj();
		insuranceList.add(insuranceObj);
		return insuranceList;
	}
	private InsuranceEntity getInsuranceObj() {
		InsuranceEntity insuranceObj=new InsuranceEntity();
		insuranceObj.setId(1L);
		insuranceObj.setInsuranceAmount(new BigDecimal(1000.0));
		insuranceObj.setInsuranceName("Test");
		insuranceObj.setValidFrom(LocalDate.now().minusYears(2));
		insuranceObj.setValidTo(LocalDate.now().plusYears(1));
		return insuranceObj;
	}
	

	
	
	

}
