package com.erp.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.ProductionEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.service.PurchaseIngredientService;
@Controller
public class PurchaseIngredientController {
	@Autowired
	private PurchaseIngredientService purchaseIngredientService;

	@GetMapping("/purchaseIngredient")
	public String purchaseIngredient() {
		return "purchaseIngredient";
	}
	@PostMapping("savePurchasedIngredient")
	@ResponseBody
	public String savePurchasedIngredient(@ModelAttribute PurchaseIngredientEntity purchasedIngredient )
	{
		//System.out.println(production);
		purchaseIngredientService.savePurchasedIngredient(purchasedIngredient);
		return "purchased Ingredient saved";
	}
}
