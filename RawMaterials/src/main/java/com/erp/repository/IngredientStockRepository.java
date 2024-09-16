package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erp.entity.IngredientEntity;
import com.erp.entity.IngredientStockEntity;
@Repository
public interface IngredientStockRepository extends JpaRepository<IngredientStockEntity, Long> {

	@Query("SELECT i FROM IngredientStockEntity i WHERE i.ingredient.id = :ingredientId")
    IngredientStockEntity findByIngredient(@Param("ingredientId") long ingredient);//edied-------!

	List<IngredientStockEntity> findByTenantId(long tenantId);

}
