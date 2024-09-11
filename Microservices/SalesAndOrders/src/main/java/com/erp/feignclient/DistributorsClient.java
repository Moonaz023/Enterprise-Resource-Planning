package com.erp.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestBody;

import com.erp.dto.DistributorEntityDTO;

@FeignClient(name = "CUSTOMERMANAGEMENT")
public interface DistributorsClient {

	@GetMapping("/admin/findDistributorByOrderId")
	DistributorEntityDTO findDistributorByOrderId(@RequestParam("distributor_id") long distributor_id, @RequestHeader("Authorization") String token);

	@PostMapping("/admin/saveUpdate")
	void saveUpdate(@RequestBody DistributorEntityDTO distributor, @RequestHeader("Authorization") String token);

	/*
	 @GetMapping("/admin/findProductNameById")
	 public String findProductNameById(@RequestParam("productId") long productId,@RequestHeader("Authorization") String token);
	 */
}
