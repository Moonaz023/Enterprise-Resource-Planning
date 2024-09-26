package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.SalesReportEntity;

import com.erp.service.SalesReportService;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class SalesReportController {
	@Autowired
	private SalesReportService salesReportService;

	/*@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}*/
	
	@GetMapping("/SalesReport")
	public String salesReport() {
		return "SalesReport";
	}
	@GetMapping("/getAllSales")
	@ResponseBody
	public List<SalesReportEntity> getAllSales(Model m,@RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		
	    List<SalesReportEntity> listOfsales = salesReportService.getAllSales(tenantId);

	    return listOfsales;
	}
	
	
	
	
	
	
	@GetMapping("/getSalesReportByDistributor")
	@ResponseBody
	List<SalesReportEntity> getSalesReportByDistributor(@RequestParam("distributor_id") long distributor_id, @RequestHeader("Authorization") String token)
	{
		return salesReportService.getSalesReportByDistributor_id(distributor_id);
	}
}
