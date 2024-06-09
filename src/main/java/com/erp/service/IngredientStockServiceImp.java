package com.erp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.repository.IngredientStockRepository;
import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientEntity;
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

	@Override
	public void modifystock_purchagedlt(IngredientEntity ingredient, double quantity) {
		IngredientStockEntity ingredientStock = ingredientStockRepository.findByIngredient(ingredient);
		
		ingredientStock.setIngredientQuantity(ingredientStock.getIngredientQuantity()-quantity);
		ingredientStockRepository.save(ingredientStock);
		
	}

	@Override
	public List<IngredientStockEntity> getAllIngredientsStock() {
		
		return ingredientStockRepository.findAll();
	}

	@Override
	public boolean checkAvailablity(List<RecipeDataDOT> recipe) {
		for(RecipeDataDOT item:recipe) {
			IngredientStockEntity ingredientStock = ingredientStockRepository.findByIngredient(item.getIngredient());
			if(ingredientStock==null || ingredientStock.getIngredientQuantity()<item.getIngredientQuantity())
			{
				return false;
			}
			
		}
		return true;
		
	}
	 

}
