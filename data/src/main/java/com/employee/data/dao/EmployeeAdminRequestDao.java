package com.employee.data.dao;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data

public class EmployeeAdminRequestDao {
	
	@NotBlank(message="empId is mandatory")
	String empId;
	
	@NotBlank(message="reportingManager is mandatory")
	String reportingManager;
	
	@NotBlank(message="deliveryUnit is mandatory")
	String deliveryUnit;
	
	
	@NotBlank(message="projectCode is mandatory")
	String projectCode;
	
	@NotBlank(message="designation is mandatory")
	String designation;


}
