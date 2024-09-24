package com.erp.feignclient;

import java.sql.Date;

import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;





@FeignClient(name = "RAWMATERIALS")
public interface RowMetaralServiceClient {

	@GetMapping("/admin/findTotalCost") // getIngredientsById/1
	public Double findTotalCost(@RequestParam("tenantId") long tenantId,@RequestHeader("Authorization") String token);
	
	@GetMapping("/admin/purchaseIngredientCostByDate")
	public Double purchaseIngredientCostByDate(@RequestParam("StartDate") Date StartDate,
											   @RequestParam("EndDate") Date EndDate,
											   @RequestParam("tenantId") long tenantId,
											   @RequestHeader("Authorization") String token);



}