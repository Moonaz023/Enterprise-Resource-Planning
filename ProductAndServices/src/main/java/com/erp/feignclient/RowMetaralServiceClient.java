package com.erp.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.dto.IngredientStocKResponseDTO;
import com.erp.dto.RecipeDataDTO;
import com.erp.entity.IngredientEntity;

@FeignClient(name = "RAWMATERIALS")
public interface RowMetaralServiceClient {

	@GetMapping("/admin/getIngredientsById/{id}") // getIngredientsById/1
	public IngredientEntity getIngredientsById(@PathVariable("id") long id,@RequestHeader("Authorization") String token);

	@GetMapping("/admin/getAllIngredientsByClint")
	public List<IngredientEntity> getAllIngredients(@RequestHeader("Authorization") String token,@RequestParam Long tenantId);

	@PostMapping("/admin/updateIngredientStock")
	public void updateIngredientStock(@RequestBody List<RecipeDataDTO> recipeData,@RequestHeader("Authorization") String token);
	
	@PostMapping("/admin/checkAvailablity")
	public boolean checkAvailablity(@RequestBody List<RecipeDataDTO> recipe,@RequestHeader("Authorization") String token);
	
	@PostMapping("/admin/updateStockByProduction")
	public IngredientStocKResponseDTO updateStockByProduction(@RequestBody List<RecipeDataDTO> recipe,@RequestHeader("Authorization") String token);
	
}