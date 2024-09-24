package com.erp.service;

import com.erp.dto.FilterByDate;
import com.erp.dto.GrossProfitDTO;
import com.erp.dto.OperatingProfitDTO;

public interface ProfitAnalysisService {

	GrossProfitDTO getLifetimeProfitData(long tenantId,String token);

	GrossProfitDTO getFilteredProfitData(FilterByDate date,long tenantId,String token);

	OperatingProfitDTO getOperatingProfit(long tenantId,String token);

	OperatingProfitDTO getOperatingProfit(FilterByDate date,long tenantId,String token);

}
