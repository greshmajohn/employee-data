package com.employee.data.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.employee.data.entity.AddressEntity;

public interface AddressRepository extends JpaRepository<AddressEntity, Long> {

}
