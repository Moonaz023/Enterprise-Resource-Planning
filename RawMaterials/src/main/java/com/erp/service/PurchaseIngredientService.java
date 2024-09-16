package com.erp.service;

import java.util.List;

import com.erp.dto.ReceivableDTO;
import com.erp.entity.PurchaseIngredientEntity;

public interface PurchaseIngredientService {

	void savePurchasedIngredient(PurchaseIngredientEntity purchasedIngredient,long tenantId);

	List<PurchaseIngredientEntity> getAllPurchasedIngredients(long tenantId);

	void deleteIngredientPurchaseRecord(Long id);

	void updateIngredientPurchase(PurchaseIngredientEntity purchasedIngredient,long tenantId);

	List<PurchaseIngredientEntity> getAllPurchageDue(long tenantId);

	void updateDue(ReceivableDTO receivableData);

}
