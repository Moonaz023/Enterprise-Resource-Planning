package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.SalesReportRepository;
@Service
public class SalesReportServiceImp implements SalesReportService{
	@Autowired
	private SalesReportRepository salesReportRepository;   
	@Override
	public List<SalesReportEntity> getAllSales(long tenantId) {
		return salesReportRepository.findByTenantId(tenantId);
	}
	@Override
	public List<SalesReportEntity> getSalesReportByDistributor(DistributorEntity distributor) {
		return salesReportRepository.findByDistributor(distributor);
	}
	@Override
	public List<SalesReportEntity> getAllSalesDue(long tenantId) {
		return salesReportRepository.findSalesDue(tenantId);
	}
	@Override
	public void updateDue(ReceivableDTO receivableData) {
		
		salesReportRepository.updateDue(receivableData.getId(),receivableData.getRecept());
		
	}
	
	

}
