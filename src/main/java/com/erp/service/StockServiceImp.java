package com.erp.service;

import org.springframework.stereotype.Service;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;
import com.erp.entity.UnitEntity;
import com.erp.repository.StockRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

@Service
public class StockServiceImp implements StockService {

	@Autowired
    private StockRepository stockRepository;
	@Override
	public void updateStock(ProductEntity product, int productionQuantity, UnitEntity unit) {
		//StockEntity stock = stockRepository.findByProduct(product);

		StockEntity stock= stockRepository.findByProductAndProductionUnit(product,unit);

        if (stock == null) {
            
            stock = new StockEntity();
            stock.setProduct(product);
            stock.setProductionUnit(unit);
            stock.setProductQuantity(productionQuantity); 
            stock.setTenantId(product.getTenantId());
        } else {
            
            stock.setProductQuantity(stock.getProductQuantity() + productionQuantity);
        }

       
        stockRepository.save(stock);
    
		
	}
	@Override//need test 2
	public void updateStockQuantity(ProductEntity product,UnitEntity unit, int productionQuantity, int productionQuantity2) {
		StockEntity stock = stockRepository.findByProductAndProductionUnit(product,unit);

        if (stock == null) {
            
            stock = new StockEntity();
            stock.setProduct(product);
            stock.setProductQuantity(productionQuantity);
            stock.setTenantId(product.getTenantId());
        } else {
            
        	 stock.setProductQuantity(stock.getProductQuantity() + (productionQuantity - productionQuantity2));
        }

        
        stockRepository.save(stock);
		
	}
	@Override//test need 3
	public void updateStockWhenProductChanged(ProductEntity oldProduct,UnitEntity oldUnit,  ProductEntity newProduct,UnitEntity newUnit, int newQuantity,
			int oldQuantity) {
		StockEntity oldStock = stockRepository.findByProductAndProductionUnit(oldProduct,oldUnit);

        if (oldStock == null) {
            
        	oldStock = new StockEntity();
        	oldStock.setProduct(oldProduct);
        	oldStock.setProductionUnit(oldUnit);
        	oldStock.setProductQuantity(oldQuantity);
        	oldStock.setTenantId(oldProduct.getTenantId());
        } else {
            
        	oldStock.setProductQuantity(oldStock.getProductQuantity() - oldQuantity);
        }
        stockRepository.save(oldStock);
        
        StockEntity newStock = stockRepository.findByProductAndProductionUnit(newProduct,newUnit);

        if (newStock == null) {
            
        	newStock = new StockEntity();
        	newStock.setProduct(newProduct);
        	newStock.setProductionUnit(newUnit);
        	newStock.setProductQuantity(newQuantity);
        	newStock.setTenantId(newProduct.getTenantId());
        } else {
            
        	newStock.setProductQuantity(newStock.getProductQuantity() + newQuantity);
        }
        stockRepository.save(newStock);
        
        
		
	}
	@Override
	public void updateStockWhenProductionDeteted(ProductEntity product,UnitEntity unit, int quantity) {
		StockEntity Stock = stockRepository.findByProductAndProductionUnit(product,unit);
		Stock.setProductQuantity(Stock.getProductQuantity() - quantity);
		stockRepository.save(Stock);
	}
	@Override
	public List<StockEntity> getAllProductsStock(long tenantId) {
		// TODO Auto-generated method stub
		return stockRepository.findByTenantId(tenantId);
	}
	

}
