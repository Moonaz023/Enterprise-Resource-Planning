package com.erp.adminController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.IngredientUseHistoryDTO;
import com.erp.dto.RecipeDataDTO;
import com.erp.entity.IngredientEntity;
import com.erp.entity.ProductionEntity;
import com.erp.feignclient.RowMetaralServiceClient;
import com.erp.service.ProductionService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ProductionController {

	@Autowired
	private ProductionService productionService;
	
	@Autowired
	private RowMetaralServiceClient rowMetaralServiceClient;

	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	@GetMapping("/production")
	public String index() {
		return "production";
	}

	@GetMapping("/getAllProductions")
	@ResponseBody
	public List<ProductionEntity> getAllproduction(Model m, HttpSession session) {
		Long tenantId = 1L;//(Long) session.getAttribute("tenantId");

		List<ProductionEntity> listOfproduct = productionService.getAllproduction(tenantId);

		return listOfproduct;
	}

	@PostMapping("/saveProduction")
	@ResponseBody
	public String saveProduct(@ModelAttribute ProductionEntity production, HttpSession session,@RequestHeader("Authorization") String token) {
		Long tenantId = 1L;//(Long) session.getAttribute("tenantId");
		System.out.println(production);
		return productionService.saveProduction(production, tenantId,token);

	}

	@PostMapping("/updateProduction")
	@ResponseBody
	public String updateProduction(@ModelAttribute ProductionEntity updatedProduction, HttpSession session) {

		productionService.updateProduction(updatedProduction);

		return "Production record updated successfully";
	}

	@DeleteMapping("/deleteProduction")
	@ResponseBody
	public String deleteProduction(@RequestParam("id") long id) {
		productionService.deleteProduction(id);
		return "Production deleted successfully";
	}
	
	@GetMapping("/getAllIngredients")
	@ResponseBody
	public List<IngredientEntity> getAllIngredients(Model m, HttpSession session,@RequestHeader("Authorization") String token) {
		Long tenantId = 1L;//(Long) session.getAttribute("tenantId");
		System.out.println(token);
		List<IngredientEntity> listOfIngredients = rowMetaralServiceClient.getAllIngredients(token);

		return listOfIngredients;
	}
	
	@GetMapping("/getAllItemUseHistory")
	@ResponseBody
	public List<IngredientUseHistoryDTO> getAllItemUseHistory(@RequestHeader("Authorization") String token) {
		Long tenantId = 1L;//(Long) session.getAttribute("tenantId");
		 List<IngredientUseHistoryDTO> ingredientUseHistoryList =new ArrayList<>();
		List<IngredientEntity> listOfIngredients = rowMetaralServiceClient.getAllIngredients(token);
		 Map<Long, String> ingredientMap = new HashMap<>();
	        for (IngredientEntity ingredient : listOfIngredients) {
	            ingredientMap.put(ingredient.getId(), ingredient.getIngredientName());
	        }

		List<ProductionEntity> listOfproduction = productionService.getAllproduction(tenantId);
		for(ProductionEntity it:listOfproduction) {
			
			for(RecipeDataDTO recipe:it.getRecipe()) {
				IngredientUseHistoryDTO ingredientUseHistoryDTO=new IngredientUseHistoryDTO();
				ingredientUseHistoryDTO.setDateOfUse(it.getDateOfProduction());
				ingredientUseHistoryDTO.setIngrediant(ingredientMap.get(recipe.getIngredient_id()));
				ingredientUseHistoryDTO.setIngredientQuantity(recipe.getIngredientQuantity());
				ingredientUseHistoryList.add(ingredientUseHistoryDTO);
			}
		}
		

		return ingredientUseHistoryList;
	}

}
