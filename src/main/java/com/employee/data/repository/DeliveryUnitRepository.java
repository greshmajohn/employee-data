package com.employee.data.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.employee.data.entity.DeliveryUnitEntity;

public interface DeliveryUnitRepository extends JpaRepository<DeliveryUnitEntity,Long> {
	
	@Query("select del from DeliveryUnitEntity del where deliveryUnit=:deloveryUnit")
	Optional<DeliveryUnitEntity> findByDeliveryunitName(String deloveryUnit);

}
