package com.employee.data.dao;


import java.time.LocalDate;

import lombok.Data;

@Data
public class EmployeeResponseDao {
	
	String empId;
	String name;
	AddressDao address;
	LocalDate DateOfBirth;
	String experience;
	String reportingManager;
	String projectCode;
	String Department;
	String ReportingHr;
	String designation;
	LocalDate dateOfJoin;
	

}
