package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.DistributorEntity;

@Repository
public interface DistributorRepository extends JpaRepository<DistributorEntity, Long> {

	List<DistributorEntity> findByTenantId(long tenantId);

}
