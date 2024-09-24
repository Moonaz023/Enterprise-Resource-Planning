package com.erp.adminController;

import java.security.Principal;
import java.sql.Date;
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

import com.erp.repository.PurchaseIngredientRepository;

//import com.erp.dto.SalesOverviewDTO;
//import com.erp.entity.SalesReportEntity;
//import com.erp.repository.SalesReportRepository;
//import com.erp.service.SalesOverviewService;
//import com.erp.repository.SalesReportRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class BusinessOverviewController {
	
	@Autowired
	private PurchaseIngredientRepository purchaseIngredientRepository;
	
	@GetMapping("/findTotalCost") 
	@ResponseBody
	public Double findTotalCost(@RequestParam("tenantId") long tenantId,@RequestHeader("Authorization") String token)
	{
		return purchaseIngredientRepository.findTotalCost(tenantId);
	}
	
	@GetMapping("/purchaseIngredientCostByDate")
	@ResponseBody
	public Double purchaseIngredientCostByDate(@RequestParam("StartDate") Date startDate,
											   @RequestParam("EndDate") Date endDate,
											   @RequestParam("tenantId") long tenantId,
											   @RequestHeader("Authorization") String token)
	{
		return purchaseIngredientRepository.findByDateRange(startDate, endDate,tenantId);
	}

}
