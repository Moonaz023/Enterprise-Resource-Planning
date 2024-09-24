package com.erp.adminController;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestHeader;
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


	@GetMapping("/getAllPurchageDue")
	@ResponseBody
	public List<PurchaseIngredientEntity> getAllPurchageDue(@RequestHeader("Authorization") String token) {
		Long tenantId = 1L;// (Long) session.getAttribute("tenantId");
		return purchaseIngredientService.getAllPurchageDue(tenantId);
	}
	@PutMapping("/updatePayDue")
	@ResponseBody
	public void updateDue(@RequestHeader("Authorization") String token,@ModelAttribute ReceivableDTO receivableData) {
		
		purchaseIngredientService.updateDue(receivableData);
		
	}
}
