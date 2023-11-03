package com.employee.data.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.employee.data.dao.DeliveryUnitDao;
import com.employee.data.dao.EmployeeAdminRequestDao;
import com.employee.data.dao.ProjectCodeDao;
import com.employee.data.dao.ResponseDto;
import com.employee.data.dao.ResponseObjectDto;
import com.employee.data.entity.DeliveryUnitEntity;
import com.employee.data.exception.DataNotFoundException;
import com.employee.data.service.EmployeeService;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;

@RestController
@RequestMapping("/employee/admin")
public class EmployeeAdminController {
	
	EmployeeService employeeService;
	
	
	
	public EmployeeAdminController(EmployeeService employeeService) {
		this.employeeService=employeeService;
	}
	
	/*
	 * update project code, delivery unit and project manager
	 */
	@PatchMapping("update-employee")
	public ResponseEntity<ResponseDto> updateEmployee(@RequestBody @Valid EmployeeAdminRequestDao employeeAdminObj) throws DataNotFoundException{
		ResponseDto res=employeeService.updateEmployeeDetails(employeeAdminObj);
		if(res.getResponseStatus().equals("Success"))
			return new ResponseEntity<ResponseDto>(res,HttpStatus.OK);
		
		return new ResponseEntity<ResponseDto>(res,HttpStatus.BAD_REQUEST);
	}
	
	/*
	 * insert delivery unit
	 */
	//@RequestBody DeliveryUnitEntity delUnit
	@PostMapping("/save-delivery-unit")
	public ResponseEntity<ResponseDto> saveDeliveryUnit(@RequestBody @Valid DeliveryUnitDao delUnit) throws DataNotFoundException{
		return new ResponseEntity<>(employeeService.addDeliveyUnit(delUnit),HttpStatus.OK);
		
	}
	
	/*
	 * update delivery unit : unit_HR and delivery_manager
	 */
	@PatchMapping("/update-delivery-unit")
	public ResponseEntity<ResponseObjectDto<DeliveryUnitEntity>> updatedDeliveryUnit(@RequestBody @Valid DeliveryUnitDao delUnit) throws DataNotFoundException{
		
		DeliveryUnitEntity deliveryResult=employeeService.updateDeliveryUnit(delUnit);
		return deliveryResult!=null?new ResponseEntity(new ResponseObjectDto(new ResponseDto("Success","Delivery Unit updated successfully !"),deliveryResult),HttpStatus.OK):
			new ResponseEntity(new ResponseObjectDto(new ResponseDto("Success","Delivery Unit updated successfully !"),null),HttpStatus.BAD_REQUEST);
		
	}
	
	@PostMapping("/save-project-code")
	public ResponseEntity<ResponseDto> saveProjectCode(@RequestBody @Valid ProjectCodeDao projectCode){
		ResponseDto resp=employeeService.saveProjectCode(projectCode);
		if(resp.getResponseStatus().equals("Success"))
			return new ResponseEntity<>(resp,HttpStatus.OK);
	
		return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
	}
	
	

}
