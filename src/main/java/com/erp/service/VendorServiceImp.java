package com.erp.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.AbstractMap.SimpleEntry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.VendorHistoryDTO;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.entity.VendorEntity;
import com.erp.repository.PurchaseIngredientRepository;
import com.erp.repository.VendorRepository;

import jakarta.persistence.EntityNotFoundException;

@Service
public class VendorServiceImp implements VendorService {
	@Autowired
	private VendorRepository vendorRepository;
	@Autowired
	private PurchaseIngredientRepository purchaseIngredientRepository;

	@Override
	public void saveVendor(VendorEntity vendor) {
		vendorRepository.save(vendor);
		
	}

	@Override
	public List<VendorEntity> getAllVendor() {
		return vendorRepository.findAll();
		 
	}

	@Override
	public void deleteVendor(long id) {
		vendorRepository.deleteById(id);
		
	}

	@Override
	public void updateVendor(VendorEntity updatedVendor) {
		vendorRepository.save(updatedVendor);
		
	}

	@Override
	public VendorHistoryDTO vendorProfile(Long id) {
		Optional<VendorEntity> vendorOptional = vendorRepository.findById(id);
		if (!vendorOptional.isPresent()) {
			throw new EntityNotFoundException("Vendor not found for ID: " + id);
		}
		VendorEntity vendor = vendorOptional.get();
		List<PurchaseIngredientEntity> purchaseReport = purchaseIngredientRepository.getByVendor(vendor);
		

		Map<SimpleEntry<String, String>, Double> productBuy = new HashMap<>();
		double totalPaid = 0;
		double totalDue = 0;

		for (PurchaseIngredientEntity report : purchaseReport) {
			totalPaid += report.getPaid();
			totalDue += report.getBill() - report.getPaid();

			String ingredientEntity = report.getIngredient().getIngredientName();
			Double quentityDouble = report.getQuantity();

			SimpleEntry<String, String> key = new SimpleEntry<>(ingredientEntity,
					report.getIngredient().getUnit().getName());
			productBuy.put(key, productBuy.getOrDefault(key, 0.0) + quentityDouble);

		}

		VendorHistoryDTO vendorHistory = new VendorHistoryDTO();
		vendorHistory.setProductBuy(productBuy);
		vendorHistory.setTotalDue(totalDue);
		vendorHistory.setTotalPaid(totalPaid);
		vendorHistory.setVendor(vendor);

		return vendorHistory;
	}
		
	

}
