package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.SalesReportEntity;
import com.erp.repository.SalesReportRepository;
@Service
public class SalesReportServiceImp implements SalesReportService{
	@Autowired
	private SalesReportRepository salesReportRepository;
	@Override
	public List<SalesReportEntity> getAllSales() {
		return salesReportRepository.findAll();
	}

}
