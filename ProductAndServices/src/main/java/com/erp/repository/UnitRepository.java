package com.erp.repository;

import java.util.List;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.erp.entity.UnitEntity;

@Repository
public interface UnitRepository extends JpaRepository<UnitEntity, Long> {

	@Query("SELECT u.name FROM UnitEntity u WHERE u.id = :id")
	String findUnitNameById(@Param("id") Long id);

	List<UnitEntity> findByTenantId(long tenantId);

	@Modifying
	@Transactional
	@Query("UPDATE  UnitEntity p set p.name= :name WHERE p.id = :id")
	void update(@Param("name")String name,@Param("id") Long id);
	
	
	/*@Query("SELECT p.name FROM ProductEntity p WHERE p.id = :productId")
    String findProductNameById(@Param("productId") Long productId);*/

}
