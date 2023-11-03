package com.employee.data.dao;

import java.util.List;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class DeliveryUnitDao {
	
	@NotBlank(message="Delivery Unit field cannot be left blank")
	String deliveryUnit;
	
	
	String deliveryManager;
	
	String unitHr;
	
}
