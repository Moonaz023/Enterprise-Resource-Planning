package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.repository.IngredientStockRepository;
import com.erp.entity.IngredientStockEntity;

@Service
public class IngredientStockServiceImp implements  IngredientStockService{
	@Autowired
	private IngredientStockRepository ingredientStockRepository;

	@Override
	public void saveIngredientStock(PurchaseIngredientEntity purchasedIngredient) {
		IngredientStockEntity ingredientStock = ingredientStockRepository.findByIngredient(purchasedIngredient.getIngredient());

        if (ingredientStock == null) {
            
        	ingredientStock = new IngredientStockEntity();
        	ingredientStock.setIngredient(purchasedIngredient.getIngredient());
        	ingredientStock.setIngredientQuantity(purchasedIngredient.getQuantity());
        } else {
        	ingredientStock.setIngredientQuantity(ingredientStock.getIngredientQuantity() + purchasedIngredient.getQuantity());
            
        }

        
        ingredientStockRepository.save(ingredientStock);
		
	}

}
