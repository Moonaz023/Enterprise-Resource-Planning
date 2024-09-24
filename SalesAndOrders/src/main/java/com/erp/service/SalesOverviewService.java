package com.erp.service;

import java.util.List;

import com.erp.dto.SalesOverviewDTO;

public interface SalesOverviewService {

	public List<SalesOverviewDTO> getSalesOverview(long tenantId);

}
