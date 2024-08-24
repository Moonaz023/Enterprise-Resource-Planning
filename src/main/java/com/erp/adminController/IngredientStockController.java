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

import com.erp.entity.IngredientStockEntity;
import com.erp.service.IngredientStockService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class IngredientStockController {
	@Autowired
	private IngredientStockService ingredientStockService;

	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/ingredientstock")
	public String ingredient_stock() {
		return "ingredientstock";
	}

	@GetMapping("/getAllIngredientStock")
	@ResponseBody
	public List<IngredientStockEntity> getAllProductsStock(Model m, HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");

		List<IngredientStockEntity> listOfIngredientsStock = ingredientStockService.getAllIngredientsStock(tenantId);

		return listOfIngredientsStock;
	}
}
