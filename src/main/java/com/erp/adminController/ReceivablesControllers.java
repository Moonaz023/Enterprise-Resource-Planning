package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.SalesReportEntity;
import com.erp.service.SalesReportService;

import jakarta.servlet.http.HttpSession;

@Controller
public class ReceivablesControllers {
	@Autowired
	private SalesReportService salesReportService;
	@GetMapping("/receivables")
	public String index() {
		return "Receivables";
	}
	@GetMapping("/getAllSalesDue")
	@ResponseBody
	public List<SalesReportEntity> getAllSales(Model m, HttpSession session) {
		
	    List<SalesReportEntity> listOfsales = salesReportService.getAllSalesDue();

	    return listOfsales;
	}
	@PutMapping("/updateDue")
	@ResponseBody
	public void updateDue(@ModelAttribute ReceivableDTO receivableData) {
		
		salesReportService.updateDue(receivableData);
		
	}
}
