package com.erp.feignclient;

import java.util.List;
import java.util.Optional;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.ProductBatchesStockEntity;
import com.erp.dto.ProductEntity;
//import com.erp.dto.ProductEntityResponse;

@FeignClient(name = "PRODUCTANDSERVICES")
public interface ProductAndServicesClient {

	@GetMapping("/admin/findProductNameById")
	public String findProductNameById(@RequestParam("productId") long productId,@RequestHeader("Authorization") String token);
	
	@GetMapping("/admin/findUnitNameById")
	public String findUnitNameById(@RequestParam("unitId") long unitId,@RequestHeader("Authorization") String token);

	@GetMapping("/admin/findProductById")
	public Optional<ProductEntity> findById(@RequestParam("productId") long productId, @RequestHeader("Authorization") String token);
	
	@GetMapping("/admin/findProductByIdd")
	public ProductEntity findByIdd(@RequestParam("productId") long productId, @RequestHeader("Authorization") String token);
	
	@GetMapping("/admin/findProductQuantityById")
	public Integer findProductQuantityById(@RequestParam("productId")long productId, @RequestParam("unitId") long unitId,@RequestHeader("Authorization") String token);
	
	@PostMapping("/admin/updateProductQuantityById")
	public void updateProductQuantityById(@RequestParam("productId")long productId, @RequestParam("unitId") long unitId, @RequestParam("stock")  int stock, @RequestHeader("Authorization") String token);

	@GetMapping("/admin/findByProductAndProductionUnit")
	public List<ProductBatchesStockEntity> findByProductAndProductionUnit(@RequestParam("productId")long productId, @RequestParam("unitId") long unitId, @RequestHeader("Authorization")  String token);

	@DeleteMapping("/admin/deleteBatch")
	public void deleteBatch(@RequestParam("id") long id,@RequestHeader("Authorization") String token);

	@PostMapping("/admin/saveBatchUpdate")
	public void saveBatchUpdate(@RequestParam("id") long id,@RequestParam("quantity") int quantity,@RequestHeader("Authorization") String token);
	
	

	
	
}