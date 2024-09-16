package com.erp.feignclient;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;

import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.entity.SalesReportResponseEntity;



@FeignClient(name = "SALESANDORDERS")
public interface SalesReportClient {

	
	@GetMapping("/admin/getSalesReportByDistributor")
	List<SalesReportResponseEntity> getSalesReportByDistributor(@RequestParam("distributor_id") long distributor_id, @RequestHeader("Authorization") String token);

/*	@GetMapping("/admin/findDistributorByOrderId")
	DistributorEntityDTO findDistributorByOrderId(@RequestParam("distributor_id") long distributor_id, @RequestHeader("Authorization") String token);
*/
}
