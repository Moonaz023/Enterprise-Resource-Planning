package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.repository.PurchaseIngredientRepository;

@Service
public class PurchaseIngredientServiceImp implements PurchaseIngredientService {

	@Autowired
	private PurchaseIngredientRepository purchaseIngredientRepository;
	@Autowired
	private IngredientStockService ingredientStockService;
	@Override
	public void savePurchasedIngredient(PurchaseIngredientEntity purchasedIngredient) {
		purchaseIngredientRepository.save(purchasedIngredient);
		ingredientStockService.saveIngredientStock(purchasedIngredient);
		
		
	}

}
