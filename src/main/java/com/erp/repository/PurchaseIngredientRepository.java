package com.erp.repository;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.entity.VendorEntity;

import jakarta.transaction.Transactional;
@Repository
public interface PurchaseIngredientRepository extends JpaRepository<PurchaseIngredientEntity, Long>{
	@Query("SELECT SUM(p.bill) FROM PurchaseIngredientEntity p")
	Double findTotalCost();
	@Query("SELECT SUM(p.bill) FROM PurchaseIngredientEntity p WHERE p.dateOfPurchase >= :startDate AND p.dateOfPurchase <= :endDate")
	Double findByDateRange(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	List<PurchaseIngredientEntity> getByVendor(VendorEntity vendor);
	@Query("SELECT p from PurchaseIngredientEntity p WHERE p.bill > p.paid")
	List<PurchaseIngredientEntity> getAllPurchageDue();
	
	@Query("UPDATE PurchaseIngredientEntity p SET p.paid = p.paid + :recept WHERE p.id = :id")
	@Modifying
	@Transactional
	void updateDue(@Param("id") long id, @Param("recept") double recept);


}