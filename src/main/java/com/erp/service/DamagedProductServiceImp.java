package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.DamagedProductEntity;
import com.erp.repository.DamagedProductRepository;

@Service
public class DamagedProductServiceImp implements DamagedProductService {
	@Autowired
	private DamagedProductRepository damagedProductRepository;

	@Override
	public void save(DamagedProductEntity damagedProduct) {
		damagedProductRepository.save(damagedProduct);

	}

}
