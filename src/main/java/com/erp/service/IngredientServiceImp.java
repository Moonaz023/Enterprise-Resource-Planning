package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.IngredientEntity;
import com.erp.repository.IngredientRepository;

@Service
public class IngredientServiceImp implements IngredientService{

	@Autowired
	private IngredientRepository ingredientRepository;
	@Override
	public void saveIngredient(IngredientEntity ingredient) {
		ingredientRepository.save(ingredient);
		
	}

}
