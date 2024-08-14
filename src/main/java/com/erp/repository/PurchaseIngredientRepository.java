package com.erp.repository;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.entity.PurchaseIngredientEntity;
@Repository
public interface PurchaseIngredientRepository extends JpaRepository<PurchaseIngredientEntity, Long>{
	@Query("SELECT SUM(p.bill) FROM PurchaseIngredientEntity p")
	Double findTotalCost();
	@Query("SELECT SUM(p.bill) FROM PurchaseIngredientEntity p WHERE p.dateOfPurchase >= :startDate AND p.dateOfPurchase <= :endDate")
	Double findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);


}