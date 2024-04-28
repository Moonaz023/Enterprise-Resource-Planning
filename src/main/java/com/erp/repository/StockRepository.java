package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long>  {

	StockEntity findByProduct(ProductEntity product);
	@Query("SELECT s.productQuantity FROM StockEntity s WHERE s.product.id = :productId")
    int findProductQuantityById(@Param("productId") Long productId);
}
