package com.erp.service;

import java.util.List;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;

public interface SalesReportService {

	List<SalesReportEntity> getAllSales(long tenantId);
	List<SalesReportEntity> getSalesReportByDistributor(DistributorEntity distributor);
	List<SalesReportEntity> getAllSalesDue(long tenantId);
	void updateDue(ReceivableDTO receivableData);
	
	

}
