package com.erp.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.FilterByDate;
import com.erp.dto.GrossProfitDTO;
import com.erp.dto.OperatingProfitDTO;
import com.erp.service.ProfitAnalysisService;

@Controller
public class ProfitAnalysisController {
	@Autowired
	private ProfitAnalysisService profitAnalysisService;
	@GetMapping("/ProfitAnalysis")
	public String index() {
		return "ProfitAnalysis";

	}
	@GetMapping("/getLifetimeProfitData")
	@ResponseBody
	public GrossProfitDTO getLifetimeProfitData()
	{
		return profitAnalysisService.getLifetimeProfitData();
	}
	@PostMapping("/getFilteredProfitData")
	@ResponseBody
	public GrossProfitDTO getFilteredProfitData(@ModelAttribute FilterByDate date)
	{
		return profitAnalysisService.getFilteredProfitData(date);
	}
	
	@GetMapping("/getOperatingProfit")
	@ResponseBody
	public OperatingProfitDTO getOperatingProfit()
	{
		return profitAnalysisService.getOperatingProfit();
	}
	
	@PostMapping("/getFilteredOperatingProfit")
	@ResponseBody
	public OperatingProfitDTO getOperatingProfit(@ModelAttribute FilterByDate date)
	{
		return profitAnalysisService.getOperatingProfit(date);
	}
	
}
