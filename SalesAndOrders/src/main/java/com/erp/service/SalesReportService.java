package com.erp.service;

import java.util.List;

import com.erp.dto.ReceivableDTO;
import com.erp.dto.DistributorEntityDTO;
import com.erp.entity.SalesReportEntity;

public interface SalesReportService {

	List<SalesReportEntity> getAllSales(long tenantId);
	List<SalesReportEntity> getSalesReportByDistributor(DistributorEntityDTO distributor);
	List<SalesReportEntity> getAllSalesDue(long tenantId);
	void updateDue(ReceivableDTO receivableData);
	
	
	List<SalesReportEntity> getSalesReportByDistributor_id(long distributor_id);
	
	

}
