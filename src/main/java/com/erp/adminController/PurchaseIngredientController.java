package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.ProductionEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.service.PurchaseIngredientService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/admin")
public class PurchaseIngredientController {
	@Autowired
	private PurchaseIngredientService purchaseIngredientService;

	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/purchaseIngredient")
	public String purchaseIngredient() {
		return "purchaseIngredient";
	}
	@PostMapping("/savePurchasedIngredient")
	@ResponseBody
	public String savePurchasedIngredient(@ModelAttribute PurchaseIngredientEntity purchasedIngredient ,HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		purchaseIngredientService.savePurchasedIngredient(purchasedIngredient,tenantId);
		return "purchased Ingredient saved";
	}
	@GetMapping("/getAllPurchasedIngredients")
	@ResponseBody
	public List<PurchaseIngredientEntity> getAllPurchasedIngredients(Model m,HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		
	    List<PurchaseIngredientEntity> listPurchasedIngredients = purchaseIngredientService.getAllPurchasedIngredients(tenantId);

	    return listPurchasedIngredients;
	}
	@DeleteMapping("/deleteIngredientPurchaseRecord")
	@ResponseBody
	public String deleteIngredientPurchaseRecord(@RequestParam Long id) {
		 purchaseIngredientService.deleteIngredientPurchaseRecord(id);
		 return "Success";
	}
	
	@PostMapping("/updateIngredientPurchase")
	@ResponseBody
	public String updateIngredientPurchase(@ModelAttribute PurchaseIngredientEntity purchasedIngredient ,HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		purchaseIngredientService.updateIngredientPurchase(purchasedIngredient,tenantId);
		return "purchased Ingredient updated";
	}
}
