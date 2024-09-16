package com.erp.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.ProductEntity;
import com.erp.entity.SalesReportResponseEntity;
import com.erp.entity.UnitEntity;

@FeignClient(name = "PRODUCTANDSERVICES")
public interface ProductAndServicesClient {

	
	@GetMapping("/admin/getProductById")
	public ProductEntity getProductById(@RequestParam("id") long id);
	
	@GetMapping("/admin/getUnitById")
	public UnitEntity getUnitById(@RequestParam("id") long id);
}
