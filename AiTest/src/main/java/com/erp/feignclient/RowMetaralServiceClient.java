package com.erp.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;


import com.erp.entity.PurchaseIngredientEntity;

import jakarta.servlet.http.HttpSession;

@FeignClient(name = "RAWMATERIALS")
public interface RowMetaralServiceClient {

	
	@GetMapping("/admin/getAllPurchasedIngredientsAndPrice")
	public List<PurchaseIngredientEntity> getAllPurchasedIngredients(@RequestHeader("tenantId") String tenant) ;
	
}