package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.StockEntity;
import com.erp.service.StockService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class StockController {
	@Autowired
    private StockService stockService;
	
//	@ModelAttribute
//	public void commonUser(Principal p, Model user) {
//		if (p != null) {
//			String name = p.getName();
//
//			user.addAttribute("user", name);
//		}
//	}
	
	@GetMapping("/stock")
	public String stock() {
		return "Stock";
	}

	@GetMapping("/getAllProductsStock")
	@ResponseBody
	public List<StockEntity> getAllProductsStock(Model m, HttpSession session ) {
		Long tenantId = 1L;//(Long) session.getAttribute("tenantId");
	    List<StockEntity> listOfproductstock = stockService.getAllProductsStock(tenantId);

	    return listOfproductstock;
	}
}
