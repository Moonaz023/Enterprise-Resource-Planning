package com.erp.service;

import java.util.List;

import com.erp.entity.DamagedProductEntity;

public interface DamagedProductService {

	void save(DamagedProductEntity damagedProduct,long tenantId);

	List<DamagedProductEntity> findAll(long tenantId);

}
