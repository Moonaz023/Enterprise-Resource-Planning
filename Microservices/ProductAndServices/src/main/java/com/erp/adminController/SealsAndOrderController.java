package com.erp.adminController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.ProductBatchesStockEntity;
import com.erp.dto.ProductBatchesStockDTO;
import com.erp.dto.ProductEntityResponse;
import com.erp.dto.SellingUnitPriceDTO;
import com.erp.dto.SellingUnitPriceResponseDTO;
import com.erp.entity.ProductEntity;
import com.erp.repository.ProductBatchesStockRepository;
import com.erp.repository.ProductRepository;
import com.erp.repository.StockRepository;
import com.erp.repository.UnitRepository;

@Controller
@RequestMapping("/admin")
public class SealsAndOrderController {

	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private UnitRepository unitRepository;
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private ProductBatchesStockRepository productBatchesStockRepository;

	@GetMapping("/findProductNameById")
	@ResponseBody
	public String findProductNameById(@RequestParam("productId") long productId,
			@RequestHeader("Authorization") String token) {
		return productRepository.findProductNameById(productId);
	}

	@GetMapping("/findUnitNameById")
	@ResponseBody
	public String findUnitNameById(@RequestParam("unitId") long unitId, @RequestHeader("Authorization") String token) {
		return unitRepository.findUnitNameById(unitId);
	}

	@GetMapping("/findProductQuantityById")
	@ResponseBody
	public Integer findProductQuantityById(@RequestParam("productId") long productId,
			@RequestParam("unitId") long unitId, @RequestHeader("Authorization") String token) {
		return stockRepository.findProductQuantityById(productId, unitId);
	}

	@GetMapping("/findProductById")
	@ResponseBody
	public Optional<ProductEntity> findById(@RequestParam("productId") long productId,
			@RequestHeader("Authorization") String token) {
		return productRepository.findById(productId);
	}

	@GetMapping("/findProductByIdd")
	@ResponseBody
	public ProductEntityResponse findByIdd(@RequestParam("productId") long productId,
			@RequestHeader("Authorization") String token) {
		ProductEntityResponse productEntityResponse = new ProductEntityResponse();
		Optional<ProductEntity> productEntityOptional = productRepository.findById(productId);
		ProductEntity productEntity = productEntityOptional.get();

		productEntityResponse.setId(productEntity.getId());
		productEntityResponse.setName(productEntity.getName());
		productEntityResponse.setProductCode(productEntity.getProductCode());
		productEntityResponse.setCategory(productEntity.getCategory());
		productEntityResponse.setTenantId(productEntity.getTenantId());

		List<SellingUnitPriceResponseDTO> unitPrice = new ArrayList<>();

		for (SellingUnitPriceDTO it : productEntity.getUnitPrice()) {
			SellingUnitPriceResponseDTO SellingUnitPriceResponseDTO = new SellingUnitPriceResponseDTO();
			SellingUnitPriceResponseDTO.setUnit(it.getUnit().getId());
			SellingUnitPriceResponseDTO.setPrice(it.getPrice());
			unitPrice.add(SellingUnitPriceResponseDTO);

		}

		productEntityResponse.setUnitPrice(unitPrice);

		return productEntityResponse;
	}

	@PostMapping("/updateProductQuantityById")
	@ResponseBody
	public void updateProductQuantityById(@RequestParam("productId") long productId,
			@RequestParam("unitId") long unitId, @RequestParam("stock") int stock,
			@RequestHeader("Authorization") String token) {
		stockRepository.updateProductQuantityByProductUnitId(productId, unitId, stock);
	}

	@GetMapping("/findByProductAndProductionUnit")
	@ResponseBody
	public List<ProductBatchesStockDTO> findByProductAndProductionUnit(@RequestParam("productId") long productId,
			@RequestParam("unitId") long unitId, @RequestHeader("Authorization") String token) {
		List<ProductBatchesStockDTO> productBatchesStockListresponse = new ArrayList<>();
		List<ProductBatchesStockEntity> productBatchesStockList = productBatchesStockRepository
				.findByProductAndProductionUnit(productId, unitId);
		for (ProductBatchesStockEntity it : productBatchesStockList) {
			ProductBatchesStockDTO productBatchesStock = new ProductBatchesStockDTO();
			productBatchesStock.setId(it.getId());
			productBatchesStock.setProduct(it.getProduct().getId());
			productBatchesStock.setProductionUnit(it.getProductionUnit().getId());
			productBatchesStock.setQuantity(it.getQuantity());
			productBatchesStock.setCostPerUnit(it.getCostPerUnit());
			productBatchesStock.setReferenceKey(it.getReferenceKey());
			productBatchesStock.setTenantId(it.getTenantId());
			productBatchesStockListresponse.add(productBatchesStock);

		}
		return productBatchesStockListresponse;
	}

	@DeleteMapping("/deleteBatch")
	@ResponseBody
	public void deleteBatch(@RequestParam("id") long id, @RequestHeader("Authorization") String token) {
		productBatchesStockRepository.deleteById(id);
	}
	
	@PostMapping("/saveBatchUpdate")
	@ResponseBody
	public void saveBatchUpdate(@RequestParam("id") long id,@RequestParam("quantity") int quantity,@RequestHeader("Authorization") String token)
	{
		productBatchesStockRepository.saveBatchUpdate(id,quantity);
	}

}
