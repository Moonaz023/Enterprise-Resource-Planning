package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.IngredientEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.repository.PurchaseIngredientRepository;

@Service
public class PurchaseIngredientServiceImp implements PurchaseIngredientService {

	@Autowired
	private PurchaseIngredientRepository purchaseIngredientRepository;
	@Autowired
	private IngredientStockService ingredientStockService;
	@Override
	public void savePurchasedIngredient(PurchaseIngredientEntity purchasedIngredient,long tenantId) {
		purchasedIngredient.setTenantId(tenantId);
		purchaseIngredientRepository.save(purchasedIngredient);
		ingredientStockService.saveIngredientStock(purchasedIngredient,tenantId);
		
		
	}
	@Override
	public List<PurchaseIngredientEntity> getAllPurchasedIngredients(long tenantId) {
		
		return purchaseIngredientRepository.findByTenantId(tenantId);
	}
	@Override
	public void deleteIngredientPurchaseRecord(Long id) {
		Optional<PurchaseIngredientEntity> purchaseIngredientOpt= purchaseIngredientRepository.findById(id);
		PurchaseIngredientEntity purchaseIngredient =purchaseIngredientOpt.get();
		//purchaseIngredient.getIngredient();
		IngredientEntity ingredient=purchaseIngredient.getIngredient();
		double quantity=purchaseIngredient.getQuantity();
		ingredientStockService.modifystock_purchagedlt(ingredient.getId(),quantity);
		purchaseIngredientRepository.deleteById(id);
		//return null;
	}
	@Override
	public void updateIngredientPurchase(PurchaseIngredientEntity purchasedIngredient,long tenantId) {
		
		
		Optional<PurchaseIngredientEntity> purchaseIngredientOpt= purchaseIngredientRepository.findById(purchasedIngredient.getId());
		PurchaseIngredientEntity old_purchaseIngredient =purchaseIngredientOpt.get();
		//purchaseIngredient.getIngredient();
		IngredientEntity ingredient=old_purchaseIngredient.getIngredient();
		double quantity=purchasedIngredient.getQuantity();
		ingredientStockService.modifystock_purchagedlt(ingredient.getId() ,quantity);
		purchasedIngredient.setTenantId(tenantId);
		purchaseIngredientRepository.save(purchasedIngredient);
		ingredientStockService.saveIngredientStock(purchasedIngredient,tenantId);
		
	}
	@Override
	public List<PurchaseIngredientEntity> getAllPurchageDue(long tenantId) {
		
		return purchaseIngredientRepository.getAllPurchageDue(tenantId);
	}
	@Override
	public void updateDue(ReceivableDTO receivableData) {
		purchaseIngredientRepository.updateDue(receivableData.getId(),receivableData.getRecept());
		
	}

}
