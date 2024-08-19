package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.service.PurchaseIngredientService;

@Controller
public class PayablesControllers {

	@Autowired
	private PurchaseIngredientService purchaseIngredientService;
	@GetMapping("/payables")
	public String index() {
		return "Payables";
	}
	@GetMapping("/getAllPurchageDue")
	@ResponseBody
	public List<PurchaseIngredientEntity> getAllPurchageDue()
	{
		return purchaseIngredientService.getAllPurchageDue();
	}
	@PutMapping("/updatePayDue")
	@ResponseBody
	public void updateDue(@ModelAttribute ReceivableDTO receivableData) {
		
		purchaseIngredientService.updateDue(receivableData);
		
	}
}
