package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.entity.IngredientBatchesStockEntity;
import com.erp.entity.IngredientEntity;
@Repository
public interface IngredientBatchesStockRepository extends JpaRepository<IngredientBatchesStockEntity, Long> {

	@Query("SELECT i FROM IngredientBatchesStockEntity i WHERE i.ingredient.id = :ingredientId")
	List<IngredientBatchesStockEntity> findByIngredient(@Param("ingredientId") long ingredient);//edied-------!


}
