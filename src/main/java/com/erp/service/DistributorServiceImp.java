package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.DistributorEntity;
import com.erp.repository.DistributorRepository;

@Service
public class DistributorServiceImp implements DistributorService
{
	@Autowired
	private DistributorRepository distributorRepository;

	@Override
	public void saveDistributor(DistributorEntity distributor) {
		distributor.setTotal_order(0);
		distributorRepository.save(distributor);
		
	}

	@Override
	public List<DistributorEntity> getAlldistributor() {
		 return distributorRepository.findAll();
	}

	@Override
	public void deleteDistributor(long id) {
		distributorRepository.deleteById(id);
		
	}

	@Override
	public void updateDistributor(DistributorEntity updatedDistributor) {
		updatedDistributor.setTotal_order(updatedDistributor.getTotal_order());
		distributorRepository.save(updatedDistributor);
		
	}
	
}
