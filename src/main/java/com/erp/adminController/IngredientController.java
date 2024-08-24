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

import com.erp.service.IngredientService;

import jakarta.servlet.http.HttpSession;

import com.erp.entity.IngredientEntity;

@Controller
@RequestMapping("/admin")
public class IngredientController {

	@Autowired
    private IngredientService ingredientService;
	
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/ingredients")
	public String Ingredients() {
		return "Ingredients";
	}
	@PostMapping("/addIngredient")
	@ResponseBody
	public String saveIngredient(@ModelAttribute IngredientEntity ingredient , HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		ingredientService.saveIngredient(ingredient,tenantId);
		return "Ingredient Saved";
	}
	@GetMapping("/getAllIngredients")
	@ResponseBody
	public List<IngredientEntity> getAllIngredients(Model m, HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		return ingredientService.getAllIngredients(tenantId);
	    
	}
	
	@PostMapping("/updateIngredient")
	@ResponseBody
	public String updateIngredient(@ModelAttribute IngredientEntity ingredient , HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		ingredientService.updateIngredient(ingredient,tenantId);
		return "Ingredient Updated";
	}
	@DeleteMapping("/deleteIngredient")
	@ResponseBody
	public String deleteIngredient(@RequestParam Long id)
	{
		ingredientService.deleteIngredient(id);
		return "Delete successful";
	}
}
