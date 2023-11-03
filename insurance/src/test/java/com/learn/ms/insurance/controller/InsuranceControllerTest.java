package com.learn.ms.insurance.controller;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.learn.ms.insurance.dao.InsuranceDto;
import com.learn.ms.insurance.repository.InsuranceRepository;
import com.learn.ms.insurance.service.InsuranceService;


@WebMvcTest(InsuranceController.class)
public class InsuranceControllerTest {
	
	@Autowired
	MockMvc mvc;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@MockBean
	InsuranceService insuranceSerice;
	
	@MockBean
	InsuranceRepository insuranceRepo;
	
//	@BeforeEach
//    void setUp() {
//        MockitoAnnotations.openMocks(this);
//    }
	
//	@Test
	public void getAllInsurance() throws Exception {
		
		List<InsuranceDto> insuranceList=getInsuranceList();
		Mockito.when(insuranceSerice.getAllInsurance(null)).thenReturn(insuranceList);
		
		
		mvc.perform(MockMvcRequestBuilders.get("/insurance/fetch-insurance")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	@Test
	public void getAllInsuranceBadRequest() throws Exception {
		
	
		Mockito.when(insuranceRepo.findAll()).thenReturn(null);
		
		mvc.perform(MockMvcRequestBuilders.get("/insurance/fetch-insurance")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}
	@Test
	public void getAllInsuranceEmpty() throws Exception {
		
		List<InsuranceDto> insuranceList=new ArrayList<InsuranceDto>();
		Mockito.when(insuranceSerice.getAllInsurance(null)).thenReturn(insuranceList);
		
		mvc.perform(MockMvcRequestBuilders.get("/insurance/fetch-insurance")
		.accept(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isBadRequest());
	}

	@Test
	public void saveInsuranceData() throws Exception {
	

		
		mvc.perform(MockMvcRequestBuilders.post("/insurance/insurance")
		.accept(MediaType.APPLICATION_JSON)
		.content(objectMapper.writeValueAsString(getInsuranceObject()))
		.contentType(MediaType.APPLICATION_JSON))
		.andDo(print())
		.andExpect(status().isOk());
	}
	private List<InsuranceDto> getInsuranceList() {
		List<InsuranceDto> insuranceList=new ArrayList<InsuranceDto>();
		insuranceList.add(getInsuranceObject());
		return insuranceList;
	}

	private InsuranceDto getInsuranceObject() {
		InsuranceDto insuranceObj = new InsuranceDto();

		insuranceObj.setInsuranceAmount(new BigDecimal(1000.0));
		insuranceObj.setInsuranceName("Test");
		insuranceObj.setValidFrom(LocalDate.now().minusYears(2));
		insuranceObj.setValidTo(LocalDate.now().plusYears(1));
		return insuranceObj;

	}
	

}
