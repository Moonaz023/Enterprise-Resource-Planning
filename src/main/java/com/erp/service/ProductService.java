package com.erp.service;

import java.util.List;


import com.erp.entity.ProductEntity;


public interface ProductService {

	void saveProduct(ProductEntity product,long tenantId);
	List<ProductEntity> getAllproduct(long tenantId);
	ProductEntity getProductById(long id);
	void updateProduct(ProductEntity updatedProduct,long tenantId);
	void deleteProduct(long id);
}
