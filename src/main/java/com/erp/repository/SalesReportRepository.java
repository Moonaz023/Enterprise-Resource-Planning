package com.erp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.erp.dto.SalesOverviewDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.SalesReportEntity;

public interface SalesReportRepository extends JpaRepository<SalesReportEntity, Long> {

	List<SalesReportEntity> findByDistributor(DistributorEntity distributor);

	@Query("SELECT new com.erp.dto.SalesOverviewDTO(s.date, s.receptAmount, s.due) FROM SalesReportEntity s")
	List<SalesOverviewDTO> getSalesOverview();
	
	

}
