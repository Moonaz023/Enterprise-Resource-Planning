package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.UnitEntity;

import jakarta.transaction.Transactional;

@Repository
public interface ProductBatchesStockRepository extends JpaRepository<ProductBatchesStockEntity, Long> {

	List<ProductBatchesStockEntity> findByProductAndProductionUnit(ProductEntity product, UnitEntity unit);

	@Modifying
	@Transactional
	@Query("UPDATE ProductBatchesStockEntity s SET s.quantity = s.quantity - :quantity WHERE s.referenceKey = :referenceKey")
	void modifyStockByProduction(@Param("referenceKey")String referenceKey, @Param("quantity")int quantity);

	ProductBatchesStockEntity findByReferenceKey(String string);

	//List<ProductBatchesStockEntity> findByProductAndProductionUnit(long productId, long unitId);
	@Query("SELECT pbs FROM ProductBatchesStockEntity pbs WHERE pbs.product.id = :productId AND pbs.productionUnit.id = :unitId")
	List<ProductBatchesStockEntity> findByProductAndProductionUnit(@Param("productId") long productId, @Param("unitId") long unitId);

	@Modifying
	@Transactional
	@Query("UPDATE ProductBatchesStockEntity pbs SET pbs.quantity = :quantity WHERE pbs.id = :id")
	void saveBatchUpdate(@Param("id") long id, @Param("quantity") int quantity);



}
