package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.SalesReportEntity;
import com.erp.service.SalesReportService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ReceivablesControllers {
	@Autowired
	private SalesReportService salesReportService;
	
	
	@GetMapping("/getAllSalesDue")
	@ResponseBody
	public List<SalesReportEntity> getAllSales(@RequestHeader("Authorization") String token,@RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");
		
	    List<SalesReportEntity> listOfsales = salesReportService.getAllSalesDue(tenantId);

	    return listOfsales;
	}
	@PutMapping("/updateDue")
	@ResponseBody
	public void updateDue(@RequestHeader("Authorization") String token,@ModelAttribute ReceivableDTO receivableData) {
		
		salesReportService.updateDue(receivableData);
		
	}
}
