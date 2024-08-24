package com.erp.service;

import com.erp.dto.FilterByDate;
import com.erp.dto.GrossProfitDTO;
import com.erp.dto.OperatingProfitDTO;

public interface ProfitAnalysisService {

	GrossProfitDTO getLifetimeProfitData(long tenantId);

	GrossProfitDTO getFilteredProfitData(FilterByDate date,long tenantId);

	OperatingProfitDTO getOperatingProfit(long tenantId);

	OperatingProfitDTO getOperatingProfit(FilterByDate date,long tenantId);

}
