package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.erp.entity.DamagedProductEntity;

@Repository
public interface DamagedProductRepository extends JpaRepository<DamagedProductEntity, Long> {

	List<DamagedProductEntity> findByTenantId(long tenantId);

}
