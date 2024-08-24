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
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.service.PurchaseIngredientService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class PayablesControllers {

	@Autowired
	private PurchaseIngredientService purchaseIngredientService;
	
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/payables")
	public String index() {
		return "Payables";
	}
	@GetMapping("/getAllPurchageDue")
	@ResponseBody
	public List<PurchaseIngredientEntity> getAllPurchageDue( HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		return purchaseIngredientService.getAllPurchageDue(tenantId);
	}
	@PutMapping("/updatePayDue")
	@ResponseBody
	public void updateDue(@ModelAttribute ReceivableDTO receivableData) {
		
		purchaseIngredientService.updateDue(receivableData);
		
	}
}
