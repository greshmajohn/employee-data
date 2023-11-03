package com.employee.data.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name="project_code")
@Data
public class ProjectCodeEntity {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	
	private Long id;
	

	@ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_unit")
	private DeliveryUnitEntity deliveryUnit;
	
	@Column(unique=true,name="project_code")
	private String projectCode;
	
	@Column(name="valid_from")
	private LocalDateTime validFrom;
	
	@Column(name="valid_to")
	private LocalDateTime validTo;
	
	@Column(name="budget")
	private BigDecimal budget;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="updated_on")
	private LocalDateTime updatedOn;

}
