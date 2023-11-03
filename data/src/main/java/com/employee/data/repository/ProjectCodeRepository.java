package com.employee.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.data.entity.ProjectCodeEntity;

public interface ProjectCodeRepository extends JpaRepository<ProjectCodeEntity, Long> {

	@Query("select f from ProjectCodeEntity f where f.projectCode=:projectCode")
	Optional<ProjectCodeEntity> findByProjectCode(String projectCode);

	
}
