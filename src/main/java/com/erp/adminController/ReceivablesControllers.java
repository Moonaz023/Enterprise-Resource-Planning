package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/receivables")
	public String index() {
		return "Receivables";
	}
	@GetMapping("/getAllSalesDue")
	@ResponseBody
	public List<SalesReportEntity> getAllSales(Model m,HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		
	    List<SalesReportEntity> listOfsales = salesReportService.getAllSalesDue(tenantId);

	    return listOfsales;
	}
	@PutMapping("/updateDue")
	@ResponseBody
	public void updateDue(@ModelAttribute ReceivableDTO receivableData) {
		
		salesReportService.updateDue(receivableData);
		
	}
}
