package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
@Repository
public interface ProductBatchesStockRepository extends JpaRepository<ProductBatchesStockEntity, Long> {

	List<ProductBatchesStockEntity> findByProduct(ProductEntity product);

}
