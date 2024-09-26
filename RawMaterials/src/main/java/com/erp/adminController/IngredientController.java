package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.service.IngredientService;
import com.erp.service.IngredientStockService;

import jakarta.servlet.http.HttpSession;

import com.erp.dto.IngredientStocKResponseDTO;
import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientEntity;

@Controller
@RequestMapping("/admin")
public class IngredientController {

	@Autowired
    private IngredientService ingredientService;
	
//	@ModelAttribute
//	public void commonUser(Principal p, Model user) {
//		if (p != null) {
//			String name = p.getName();
//
//			user.addAttribute("user", name);
//		}
//	}
	
	@GetMapping("/ingredients")
	public String Ingredients() {
		return "Ingredients";
	}
	@PostMapping("/addIngredient")
	@ResponseBody
	public String saveIngredient(@ModelAttribute IngredientEntity ingredient ,@RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		ingredientService.saveIngredient(ingredient,tenantId);
		return "Ingredient Saved";
	}
	@GetMapping("/getAllIngredients")
	@ResponseBody
	public List<IngredientEntity> getAllIngredients(Model m,@RequestHeader("Authorization") String token, @RequestHeader("tenantId") String tenant ) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		return ingredientService.getAllIngredients(tenantId);
	    
	}
	
	@GetMapping("/getAllIngredientsByClint")
	@ResponseBody
	public List<IngredientEntity> getAllIngredientsByClint(Model m,@RequestHeader("Authorization") String token,@RequestParam Long tenantId ) {
		//Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		return ingredientService.getAllIngredients(tenantId);
	    
	}
	
	@GetMapping("/getIngredientsById/{id}")//getIngredientsById/5
	//@GetMapping("/getIngredientsById")//getIngredientsById?id=1
	@ResponseBody
	public IngredientEntity getIngredientsById(@PathVariable("id") long id) {
	//public IngredientEntity getIngredientsById(@RequestParam("id") long id, HttpSession session) {
		
		return ingredientService.getIngredientsById(id);
	    
	}
	
	@PostMapping("/updateIngredient")
	@ResponseBody
	public String updateIngredient(@ModelAttribute IngredientEntity ingredient ,  @RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
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
	
	
	
	@Autowired
	IngredientStockService ingredientStockService;

	@PostMapping("/checkAvailablity")
	@ResponseBody
	public Boolean checkAvailablity(@RequestBody List<RecipeDataDOT> recipe,@RequestHeader("Authorization") String token) {

		return ingredientStockService.checkAvailablity(recipe);

	}

	@PostMapping("/updateStockByProduction")
	@ResponseBody
	public IngredientStocKResponseDTO updateStockByProduction(@RequestBody List<RecipeDataDOT> recipe,@RequestHeader("Authorization") String token) {
		return ingredientStockService.updateStockByProduction(recipe);
	}
}
