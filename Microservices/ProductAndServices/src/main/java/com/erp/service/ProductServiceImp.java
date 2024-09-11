package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.ProductEntity;
import com.erp.repository.ProductRepository;

@Service
public class ProductServiceImp implements ProductService {
	@Autowired
	private ProductRepository productRepository;
	
	
	public ProductServiceImp(ProductRepository productRepository) {
	    this.productRepository = productRepository;
	}

	 @Override
	 public void saveProduct(ProductEntity product,long tenantId) {
		 product.setTenantId(tenantId);
	      
		 productRepository.save(product);
	 }
	 @Override
	 public List<ProductEntity> getAllproduct(long tenantId) {
	     return productRepository.findByTenantId(tenantId);
	 }
	 @Override
	 public ProductEntity getProductById(long id) {
	        Optional<ProductEntity> productOptional = productRepository.findById(id);
	        return productOptional.orElse(null);
	    }

	@Override
	public void updateProduct(ProductEntity updatedProduct,long tenantId) {
		updatedProduct.setTenantId(tenantId);
		 productRepository.save(updatedProduct);
	}

	@Override
	public void deleteProduct(long id) {
		productRepository.deleteById(id);
		
	}
	 

}
