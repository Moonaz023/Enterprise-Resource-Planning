package com.erp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.erp.entity.SalesReportEntity;

public interface SalesReportRepository extends JpaRepository<SalesReportEntity, Long> {

}
