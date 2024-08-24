package com.erp.adminController;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.FilterByDate;
import com.erp.dto.GrossProfitDTO;
import com.erp.dto.OperatingProfitDTO;
import com.erp.service.ProfitAnalysisService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ProfitAnalysisController {
	@Autowired
	private ProfitAnalysisService profitAnalysisService;
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/ProfitAnalysis")
	public String index() {
		return "ProfitAnalysis";

	}
	@GetMapping("/getLifetimeProfitData")
	@ResponseBody
	public GrossProfitDTO getLifetimeProfitData(HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		return profitAnalysisService.getLifetimeProfitData(tenantId);
	}
	@PostMapping("/getFilteredProfitData")
	@ResponseBody
	public GrossProfitDTO getFilteredProfitData(@ModelAttribute FilterByDate date,HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		return profitAnalysisService.getFilteredProfitData(date,tenantId);
	}
	
	@GetMapping("/getOperatingProfit")
	@ResponseBody
	public OperatingProfitDTO getOperatingProfit(HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		return profitAnalysisService.getOperatingProfit(tenantId);
	}
	
	@PostMapping("/getFilteredOperatingProfit")
	@ResponseBody
	public OperatingProfitDTO getOperatingProfit(@ModelAttribute FilterByDate date,HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		return profitAnalysisService.getOperatingProfit(date,tenantId);
	}
	
}
