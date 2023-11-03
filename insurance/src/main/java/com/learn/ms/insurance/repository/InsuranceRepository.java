package com.learn.ms.insurance.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.learn.ms.insurance.entity.InsuranceEntity;

public interface InsuranceRepository extends JpaRepository<InsuranceEntity, Long> {

}
