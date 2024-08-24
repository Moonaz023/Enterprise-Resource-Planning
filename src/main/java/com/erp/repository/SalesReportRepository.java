package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.erp.dto.SalesOverviewDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;

import jakarta.transaction.Transactional;

public interface SalesReportRepository extends JpaRepository<SalesReportEntity, Long> {

	List<SalesReportEntity> findByDistributor(DistributorEntity distributor);

	@Query("SELECT new com.erp.dto.SalesOverviewDTO(s.date, s.receptAmount, s.due) FROM SalesReportEntity s WHERE s.tenantId = :tenantId")
	List<SalesOverviewDTO> getSalesOverview(@Param("tenantId") long tenantId);

	@Query("SELECT s FROM SalesReportEntity s WHERE s.date >= :startDate AND s.date <= :endDate AND s.tenantId= :tenantId")
	List<SalesReportEntity> findByDateRange(@Param("startDate") String startDate, @Param("endDate") String endDate,@Param("tenantId") long tenantId);

	@Query("SELECT s FROM SalesReportEntity s WHERE s.due > 0 AND s.tenantId=:tenantId")
	List<SalesReportEntity> findSalesDue(@Param("tenantId") long tenantId);

	
	@Query("UPDATE SalesReportEntity s SET s.due = s.due - :recept , s.receptAmount= s.receptAmount + :recept WHERE s.id = :id")
	@Modifying
	@Transactional
	void updateDue(@Param("id") long id, @Param("recept") double recept);

	List<SalesReportEntity> findByTenantId(long tenantId);

	
	

	//List<SalesReportEntity> findByDateRange(String startDate, String endDate, long tenantId);

}
