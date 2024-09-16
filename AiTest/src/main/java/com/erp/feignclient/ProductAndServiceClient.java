package com.erp.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.IngredientUseHistoryDTO;

@FeignClient(name = "PRODUCTANDSERVICES")
public interface ProductAndServiceClient {
	@GetMapping("/admin/getAllItemUseHistory")
	public List<IngredientUseHistoryDTO> getAllItemUseHistory(@RequestHeader("Authorization") String token);
}
