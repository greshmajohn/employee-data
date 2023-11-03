package com.employee.data.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
/*
 * entity class having delivery unit information
 */

@Entity
@Table(name="delivery_unit")
@Data
public class DeliveryUnitEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	@Column(unique=true,name="delivery_unit")
	private String deliveryUnit;
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="unit_hr")
	@JsonBackReference
	private EmployeeEntity unitHr;
	
	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_manager")
	@JsonBackReference
	private EmployeeEntity deliveryManager;

}
