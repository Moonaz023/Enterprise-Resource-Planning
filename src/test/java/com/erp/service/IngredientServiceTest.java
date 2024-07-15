package com.erp.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.assertj.core.internal.Arrays;
import java.util.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.autoconfigure.condition.ConditionalOnWebApplication;

import com.erp.entity.IngredientEntity;
import com.erp.repository.IngredientRepository;
@ExtendWith(MockitoExtension.class)
public class IngredientServiceTest {

	@Mock
	private IngredientRepository ingredientRepository;
	@InjectMocks
	private IngredientServiceImp ingredientService;
	private IngredientEntity ingredient;
	@BeforeEach
	void setUp() {
		ingredient=IngredientEntity.builder()
				.id(1)
				.ingredientCode("In001")
				.ingredientName("abc")
				.build();

	}

	@Test
	public void testSaveIngredient() {

		ingredientService.saveIngredient(ingredient);
		verify(ingredientRepository, times(1)).save(ingredient);

	}

	@Test
	public void testGetAllIngredients()
	{
		List<IngredientEntity> ingredientList=new ArrayList<>();
		ingredientList.add(ingredient);
		when(ingredientRepository.findAll()).thenReturn(ingredientList);

		List<IngredientEntity> result=ingredientService.getAllIngredients();

		assertEquals(ingredientList, result);
		assertEquals(ingredient, result.get(0));

	}
	@Test
	public void testDeleteIngredientById()
	{
		ingredientService.deleteIngredient(1L);
		verify(ingredientRepository, times(1)).deleteById(1L);
	}

	@Test
	public void testUpdateIngredient()
	{
		
	}
	

}
