package com.erp.service;

import java.util.List;

import org.springframework.web.bind.annotation.RequestHeader;

import com.erp.entity.ProductionEntity;

public interface ProductionService {

	public String saveProduction(ProductionEntity production,long tenantId,@RequestHeader("Authorization") String token);
	List<ProductionEntity> getAllproduction(long tenantId);
	public ProductionEntity getProductionById(long id);
	void updateProduction(ProductionEntity updatedProduction);
	void deleteProduction(long id);
	
	    
}
