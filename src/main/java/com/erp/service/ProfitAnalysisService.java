package com.erp.service;

import com.erp.dto.FilterByDate;
import com.erp.dto.GrossProfitDTO;
import com.erp.dto.OperatingProfitDTO;

public interface ProfitAnalysisService {

	GrossProfitDTO getLifetimeProfitData();

	GrossProfitDTO getFilteredProfitData(FilterByDate date);

	OperatingProfitDTO getOperatingProfit();

	OperatingProfitDTO getOperatingProfit(FilterByDate date);

}
