package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.ProductionEntity;

public interface ProductionRepository extends JpaRepository<ProductionEntity, Long> {

	List<ProductionEntity> findByTenantId(long tenantId);

}
