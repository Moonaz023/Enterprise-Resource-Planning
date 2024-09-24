package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.SalesOverviewDTO;
import com.erp.repository.SalesReportRepository;

@Service
public class SalesOverviewServiceImp implements SalesOverviewService {

	@Autowired
	private SalesReportRepository salesReportRepository;
	@Override
	public List<SalesOverviewDTO> getSalesOverview(long tenantId) {
		
		return salesReportRepository.getSalesOverview(tenantId);
	}

}
