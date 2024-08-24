package com.erp.service;

import java.util.List;

import com.erp.entity.IngredientEntity;

public interface IngredientService {

	void saveIngredient(IngredientEntity ingredient,long tenantId);

	List<IngredientEntity> getAllIngredients(long tenantId);

	void deleteIngredient(Long id);

	void updateIngredient(IngredientEntity ingredient,long tenantId);

}
