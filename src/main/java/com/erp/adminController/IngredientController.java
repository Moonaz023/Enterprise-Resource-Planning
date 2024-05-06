package com.erp.adminController;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.service.IngredientService;
import com.erp.entity.IngredientEntity;

@Controller
public class IngredientController {

	@Autowired
    private IngredientService ingredientService;
	@GetMapping("/ingredients")
	public String Ingredients() {
		return "Ingredients";
	}
	@PostMapping("/addIngredient")
	@ResponseBody
	public String saveIngredient(@ModelAttribute IngredientEntity ingredient )
	{
		ingredientService.saveIngredient(ingredient);
		return "Ingredient Saved";
	}
}
