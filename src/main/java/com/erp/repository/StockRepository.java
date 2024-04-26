package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;

public interface StockRepository extends JpaRepository<StockEntity, Long>  {

	StockEntity findByProduct(ProductEntity product);

}
