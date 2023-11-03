package com.learn.ms.insurance.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
/*
 * author @greshma.john
 * 
 *entity class for table insurance
 */
import lombok.Data;

@Entity
@Table(name="insurance")
@Data
public class InsuranceEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private Long id;
	
	
	@Column(name="uuid")
	private String uuId = UUID.randomUUID().toString();
	
	@Column(name="insurance_name")
	private String insuranceName;
	
	@Column(name="amount")
	private BigDecimal insuranceAmount;
	
	@Column(name="max_insured_dependents")
	private int maxInsurance;
	
	@Column(name="valid_from")
	private LocalDate validFrom;
	
	@Column(name="valid_to")
	private LocalDate validTo;
	
	@Column(name="created_on")
	private LocalDateTime createdOn;
	
	@Column(name="created_by")
	private String createdBy;
	
	@Column(name="updated_by")
	private String updatedBy;
	
	@Column(name="update_on")
	private LocalDateTime updatedOn;
	
//	@PrePersist
//    private void assignUIID(){
//		uuId=UUID.randomUUID();
//    }
//	

}
