package com.employee.data.dao;

import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class EmployeeRequestDao {
	
	@NotBlank(message = "First Name is mandatory")
	String firstName;
	@NotBlank(message = "Last Name is mandatory")
	String lastName;
	
	@NotBlank(message = "Mandatory field is missing")
	String street;
	
	@NotBlank(message = "Mandatory field is missing")
	String city;
	
	
	@Min(value=10000,message = "pincode should be 5 digit")
	@Max(value=99999,message = "pincode should be 5 digit")
	int pincode;
	
	String state;
	String country;
	
	@Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
	String phoneNo;
	
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	LocalDate DateOfBirth;
	
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	LocalDate DateOfJoin;
	
	@Min(1) 
	int experienceInMonths;

	

}
