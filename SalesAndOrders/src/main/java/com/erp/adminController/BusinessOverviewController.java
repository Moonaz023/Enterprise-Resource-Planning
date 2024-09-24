package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.SalesOverviewDTO;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.SalesReportRepository;
import com.erp.service.SalesOverviewService;
import com.erp.repository.SalesReportRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BusinessOverviewController {
	@Autowired
	private SalesOverviewService salesOverviewService;
	@Autowired
	private SalesReportRepository profitAnalysis;
	

//	@ModelAttribute
//	public void commonUser(Principal p, Model user) {
//		if (p != null) {
//			String name = p.getName();
//
//			user.addAttribute("user", name);
//		}
//	}

//	@GetMapping("/SalesOverview")
//	public String index() {
//		return "SalesOverview";
//
//	}

	
	@GetMapping("/getSalesOverview")
	@ResponseBody
	public List<SalesOverviewDTO> getSalesOverview(HttpSession session) {
		Long tenantId = 1L;// (Long) session.getAttribute("tenantId");
		List<SalesOverviewDTO> salesOverview = salesOverviewService.getSalesOverview(tenantId);
		return salesOverview;
	}
	
	
	@GetMapping("/getSalesReportByTenantId")
	@ResponseBody
	List<SalesReportEntity> getSalesReportByTenantId(@RequestParam("tenantId") long tenantId, @RequestHeader("Authorization") String token)
	{
		return profitAnalysis.findByTenantId(tenantId);
	}


	
	@GetMapping("/getSalesReportByDateRange")
	@ResponseBody
	List<SalesReportEntity>getSalesReportByDateRange(@RequestParam("StartDate") String StartDate,
														@RequestParam("EndDate") String EndDate,
														@RequestParam("tenantId") long tenantId,
														@RequestHeader("Authorization") String token)
	{
		return profitAnalysis.findByDateRange(StartDate,EndDate,tenantId);
	}
	
}
