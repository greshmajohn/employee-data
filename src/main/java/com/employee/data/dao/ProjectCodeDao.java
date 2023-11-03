package com.employee.data.dao;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ProjectCodeDao {
	
	@NotBlank(message="Delivery Unit cann not be left blank")
	String deliveryUnit;
	
	@NotBlank(message="Project Code can not beft blank")
	String projectCode;
	
	
	@Min(value=1000,message = "Invalid budget")
	BigDecimal budget;
	
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	@Future(message="Not a valid date")
	LocalDate validFrom;
	
	@JsonDeserialize(using=LocalDateDeserializer.class)
	@JsonSerialize(using=LocalDateSerializer.class)
	@Future(message="Not a valid date")
	LocalDate validTo;
	

}
