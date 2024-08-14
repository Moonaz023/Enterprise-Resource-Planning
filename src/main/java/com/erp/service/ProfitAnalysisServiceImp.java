package com.erp.service;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.FilterByDate;
import com.erp.dto.GrossProfitDTO;
import com.erp.dto.OperatingProfitDTO;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.PurchaseIngredientRepository;
import com.erp.repository.SalesReportRepository;

@Service
public class ProfitAnalysisServiceImp implements ProfitAnalysisService {
	@Autowired
	private SalesReportRepository salesReportRepository;
	@Autowired
	private PurchaseIngredientRepository purchaseIngredientRepository;

	@Override
	public GrossProfitDTO getLifetimeProfitData() {
		double Sales = 0;
		double COGS = 0;
		double GP = 0;
		List<SalesReportEntity> allSelSales = salesReportRepository.findAll();

		for (SalesReportEntity it : allSelSales) {
			Sales += it.getDue() + it.getReceptAmount();
			COGS += it.getCost();
		}
		GP = Sales - COGS;

		GrossProfitDTO grossProfitData = new GrossProfitDTO();
		grossProfitData.setCOGS(COGS);
		grossProfitData.setGP(GP);
		grossProfitData.setSales(Sales);

		return grossProfitData;
	}

	@Override
	public GrossProfitDTO getFilteredProfitData(FilterByDate date) {
		double Sales = 0;
		double COGS = 0;
		double GP = 0;
		List<SalesReportEntity> allSelSales = salesReportRepository.findByDateRange(date.getStartDate(),
				date.getEndDate());

		for (SalesReportEntity it : allSelSales) {
			Sales += it.getDue() + it.getReceptAmount();
			COGS += it.getCost();
		}
		GP = Sales - COGS;

		GrossProfitDTO grossProfitData = new GrossProfitDTO();
		grossProfitData.setCOGS(COGS);
		grossProfitData.setGP(GP);
		grossProfitData.setSales(Sales);

		return grossProfitData;
	}

	@Override
	public OperatingProfitDTO getOperatingProfit() {
		GrossProfitDTO grossProfitData = getLifetimeProfitData();
		double totalExpence = grossProfitData.getCOGS();
		OperatingProfitDTO operatingProfitobj = new OperatingProfitDTO();
		operatingProfitobj.setGrossProfit(grossProfitData.getGP());

		Double purchaseIngredientCost = purchaseIngredientRepository.findTotalCost();
		if(purchaseIngredientCost==null)
			purchaseIngredientCost=0.0;
		totalExpence += purchaseIngredientCost;
		// Add all expeces here........

		operatingProfitobj.setOperatingExpenses(totalExpence);

		operatingProfitobj.setOperatingProfit(operatingProfitobj.getGrossProfit() - totalExpence);

		return operatingProfitobj;
	}

	@Override
	public OperatingProfitDTO getOperatingProfit(FilterByDate date) {
		GrossProfitDTO grossProfitData = getFilteredProfitData(date);
		double totalExpence = grossProfitData.getCOGS();
		OperatingProfitDTO operatingProfitobj = new OperatingProfitDTO();
		operatingProfitobj.setGrossProfit(grossProfitData.getGP());

		Date startDate = Date.valueOf(date.getStartDate());
		Date endDate = Date.valueOf(date.getEndDate());

		Double purchaseIngredientCost = purchaseIngredientRepository.findByDateRange(startDate, endDate);
		if (purchaseIngredientCost == null)
			purchaseIngredientCost = 0.0;
		totalExpence += purchaseIngredientCost;
		// Add all expeces here........

		operatingProfitobj.setOperatingExpenses(totalExpence);

		operatingProfitobj.setOperatingProfit(operatingProfitobj.getGrossProfit() - totalExpence);

		return operatingProfitobj;
	}

}
