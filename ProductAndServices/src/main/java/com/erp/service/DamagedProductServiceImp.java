package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.DamagedProductEntity;
import com.erp.repository.DamagedProductRepository;
import com.erp.repository.ProductBatchesStockRepository;
//import com.erp.repository.ProductBatchesStockRepository;

@Service
public class DamagedProductServiceImp implements DamagedProductService {
	
	@Autowired
	private DamagedProductRepository damagedProductRepository;
	@Autowired
	private StockService stockService;
	
	
	@Autowired
	private ProductBatchesStockRepository productBatchesStockRepository;

	@Override
	public void save(DamagedProductEntity damagedProduct,long tenantId) {
		damagedProduct.setTenantId(tenantId);
		damagedProductRepository.save(damagedProduct);
		stockService.updateStockWhenProductionDeteted(damagedProduct.getProductionId().getProduct(),damagedProduct.getProductionId().getProductionUnit(),damagedProduct.getQuantity());
		String refKey="Production-"+String.valueOf(damagedProduct.getProductionId().getId());
		productBatchesStockRepository.modifyStockByProduction(refKey,damagedProduct.getQuantity());
		

	}

	@Override
	public List<DamagedProductEntity> findAll(long tenantId ) {

		return damagedProductRepository.findByTenantId(tenantId);
	}

}
