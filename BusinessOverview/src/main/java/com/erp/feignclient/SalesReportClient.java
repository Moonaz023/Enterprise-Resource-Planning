package com.erp.feignclient;

import java.sql.Date;
import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;


import com.erp.entity.SalesReportEntity;



@FeignClient(name = "SALESANDORDERS")
public interface SalesReportClient {

	


	
	@GetMapping("/admin/getSalesReportByTenantId")
	List<SalesReportEntity> getSalesReportByTenantId(@RequestParam("tenantId") long tenantId, @RequestHeader("Authorization") String token);


	
	@GetMapping("/admin/getSalesReportByDateRange")
	List<SalesReportEntity>getSalesReportByDateRange(@RequestParam("StartDate") String StartDate,
														@RequestParam("EndDate") String EndDate,
														@RequestParam("tenantId") long tenantId,
														@RequestHeader("Authorization") String token);
	
}
