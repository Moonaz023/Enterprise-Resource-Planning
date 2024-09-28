package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;
import com.erp.entity.UnitEntity;

import jakarta.transaction.Transactional;

public interface StockRepository extends JpaRepository<StockEntity, Long>  {

	StockEntity findByProduct(ProductEntity product);
	
	StockEntity findByProductAndProductionUnit(ProductEntity product, UnitEntity unit);
	
	@Query("SELECT s.productQuantity FROM StockEntity s WHERE s.product.id = :productId and s.productionUnit.id =:unitId")
    Integer findProductQuantityById(@Param("productId") Long productId,@Param("unitId") Long unitId);
	
	@Modifying
    @Transactional
    @Query("UPDATE StockEntity s SET s.productQuantity = :stock WHERE s.product = :product and s.productionUnit.id =:unit")
	void updateProductQuantityById(@Param("product") ProductEntity product,@Param("unit") Long unit, @Param("stock") int stock);

	List<StockEntity> findByTenantId(long tenantId);

	@Modifying
	@Transactional
	@Query("UPDATE StockEntity s SET s.productQuantity = :stock WHERE s.product.id = :productId AND s.productionUnit.id = :unitId")
	void updateProductQuantityByProductUnitId(@Param("productId") long productId, @Param("unitId") long unitId, @Param("stock") int stock);

	//void updateProductQuantityByProductUnotId(long productId, long unitId, int stock);

	//StockEntity findByProductAndUnit(ProductEntity product, UnitEntity unit);
	

}
