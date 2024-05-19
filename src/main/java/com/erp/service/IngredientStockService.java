package com.erp.service;

import com.erp.entity.IngredientEntity;
import com.erp.entity.PurchaseIngredientEntity;

public interface IngredientStockService {

	void saveIngredientStock(PurchaseIngredientEntity purchasedIngredient);

	void modifystock_purchagedlt(IngredientEntity ingredient, double quantity);

}
