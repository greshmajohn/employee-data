package com.employee.data.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
/*
 * entity having employee details
 */

@Entity
@Table(name="emp")
@Data
public class EmployeeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
//	
	@Column(name="emp_id")
	private String empId;
	
	@Column(name="emp_name")
	private String empName;
	
	@Column(name="date_of_birth")
	private LocalDate dateOfBirth;
	
	@Column(name="date_of_join")
	private LocalDate dateOfJoin;
	
	@Column(name="experience")
	private int experience;
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="address")
	private AddressEntity address;
	

	@OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="reporting_manager")
	@JsonManagedReference
	private EmployeeEntity reportingManager;
	

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="project_code")
	private ProjectCodeEntity projectCode;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name="delivery_unit")
	@JsonManagedReference
	private DeliveryUnitEntity deliveryUnit;

	@Column(name="designation")
	private String designation;
	
	@Column(name="created_by")
	private String createdBy; 
	
	@Column(name="created_on")
	private LocalDateTime createdOn; 
	
	@Column(name="updated_by")
	private String updatedBy; 
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn; 
	
	

}
