package com.erp.service;

import java.util.List;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;

public interface SalesReportService {

	List<SalesReportEntity> getAllSales();
	List<SalesReportEntity> getSalesReportByDistributor(DistributorEntity distributor);
	List<SalesReportEntity> getAllSalesDue();
	void updateDue(ReceivableDTO receivableData);
	
	

}
