package com.erp.service;

import java.util.List;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;

public interface DistributorService {

	void saveDistributor(DistributorEntity distributor, long tenantId);

	List<DistributorEntity> getAlldistributor(long tenantId);

	void deleteDistributor(long id);

	void updateDistributor(DistributorEntity updatedDistributor,long tenantId);
	public DistributorHistoryDTO distributorProfile(Long id,String token);

	
}
