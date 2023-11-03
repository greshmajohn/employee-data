package com.employee.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.data.entity.EmployeeEntity;

public interface EmployeeReposiory extends JpaRepository<EmployeeEntity, Long> {

	@Query("Select f from EmployeeEntity f where f.empId=:empId")
	Optional<EmployeeEntity> findEmployeeByEmpId(String empId);

	@Query("Select f from EmployeeEntity f where f.empName=:reportingManager")
	Optional<EmployeeEntity> findByEmployeeName(String reportingManager);
	
	@Query("Select f from EmployeeEntity f where f.empName=:empName and f.designation=:destination")
	Optional<EmployeeEntity> findEmployeeByNameAndDesignation(String empName,String destination);

}
