package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;
import com.erp.entity.UnitEntity;
import com.erp.repository.ProductionRepository;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductionServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
public class ProductionServiceImpDiffblueTest {
    @MockBean
    private IngredientStockService ingredientStockService;

    @MockBean
    private ProductionRepository productionRepository;

    @Autowired
    private ProductionServiceImp productionServiceImp;

    @MockBean
    private StockService stockService;

    /**
     * Method under test:
     * {@link ProductionServiceImp#saveProduction(ProductionEntity)}
     */
    @Test
    public void testSaveProduction() {
        // Arrange
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(true);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity);
        doNothing().when(stockService).updateStock(Mockito.<ProductEntity>any(), anyInt(), Mockito.<UnitEntity>any());

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());
        product2.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit2 = new UnitEntity();
        productionUnit2.setCf(10.0d);
        productionUnit2.setId(1L);
        productionUnit2.setIngredientEntity(new ArrayList<>());
        productionUnit2.setName("Name");
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setProduct(product2);
        production.setProductionQuantity(1);
        production.setProductionUnit(productionUnit2);
        production.setRecipe(new ArrayList<>());
        production.setUnitCost(10.0d);

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        verify(stockService).updateStock(isA(ProductEntity.class), eq(1), isA(UnitEntity.class));
        verify(productionRepository).save(isA(ProductionEntity.class));
        assertEquals("ok", actualSaveProductionResult);
        assertEquals(0.0d, production.getUnitCost(), 0.0);
    }

    /**
     * Method under test:
     * {@link ProductionServiceImp#saveProduction(ProductionEntity)}
     */
    @Test
    public void testSaveProduction2() {
        // Arrange
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(false);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setProduct(product);
        production.setProductionQuantity(1);
        production.setProductionUnit(productionUnit);
        production.setRecipe(new ArrayList<>());
        production.setUnitCost(10.0d);

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        assertEquals("no", actualSaveProductionResult);
    }

    /**
     * Method under test:
     * {@link ProductionServiceImp#saveProduction(ProductionEntity)}
     */
    @Test
    public void testSaveProduction3() {
        // Arrange
        when(ingredientStockService.modifystock_purchagedlt(Mockito.<IngredientEntity>any(), anyDouble()))
                .thenReturn(10.0d);
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(true);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity);
        doNothing().when(stockService).updateStock(Mockito.<ProductEntity>any(), anyInt(), Mockito.<UnitEntity>any());

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());
        product2.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit2 = new UnitEntity();
        productionUnit2.setCf(10.0d);
        productionUnit2.setId(1L);
        productionUnit2.setIngredientEntity(new ArrayList<>());
        productionUnit2.setName("Name");
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        UnitEntity unit = new UnitEntity();
        unit.setCf(10.0d);
        unit.setId(1L);
        unit.setIngredientEntity(new ArrayList<>());
        unit.setName("ok");
        unit.setProductionEntity(new ArrayList<>());
        unit.setStockEntity(new ArrayList<>());

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("ok");
        ingredient.setIngredientName("ok");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());
        ingredient.setUnit(unit);

        RecipeDataDOT recipeDataDOT = new RecipeDataDOT();
        recipeDataDOT.setIngredient(ingredient);
        recipeDataDOT.setIngredientQuantity(10.0d);

        ArrayList<RecipeDataDOT> recipe = new ArrayList<>();
        recipe.add(recipeDataDOT);

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setProduct(product2);
        production.setProductionQuantity(1);
        production.setProductionUnit(productionUnit2);
        production.setRecipe(recipe);
        production.setUnitCost(10.0d);

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        verify(ingredientStockService).modifystock_purchagedlt(isA(IngredientEntity.class), eq(10.0d));
        verify(stockService).updateStock(isA(ProductEntity.class), eq(1), isA(UnitEntity.class));
        verify(productionRepository).save(isA(ProductionEntity.class));
        assertEquals("ok", actualSaveProductionResult);
    }

    /**
     * Method under test: {@link ProductionServiceImp#getAllproduction()}
     */
    @Test
    public void testGetAllproduction() {
        // Arrange
        ArrayList<ProductionEntity> productionEntityList = new ArrayList<>();
        when(productionRepository.findAll()).thenReturn(productionEntityList);

        // Act
        List<ProductionEntity> actualAllproduction = productionServiceImp.getAllproduction();

        // Assert
        verify(productionRepository).findAll();
        assertTrue(actualAllproduction.isEmpty());
        assertSame(productionEntityList, actualAllproduction);
    }

    /**
     * Method under test: {@link ProductionServiceImp#getProductionById(long)}
     */
    @Test
    public void testGetProductionById() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        ProductionEntity actualProductionById = productionServiceImp.getProductionById(1L);

        // Assert
        verify(productionRepository).findById(eq(1L));
        assertSame(productionEntity, actualProductionById);
    }

    /**
     * Method under test:
     * {@link ProductionServiceImp#updateProduction(ProductionEntity)}
     */
    @Test
    public void testUpdateProduction() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());
        product2.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit2 = new UnitEntity();
        productionUnit2.setCf(10.0d);
        productionUnit2.setId(1L);
        productionUnit2.setIngredientEntity(new ArrayList<>());
        productionUnit2.setName("Name");
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity2 = new ProductionEntity();
        productionEntity2.setDamagedProduct(new ArrayList<>());
        productionEntity2.setDateOfProduction(mock(Date.class));
        productionEntity2.setId(1L);
        productionEntity2.setProduct(product2);
        productionEntity2.setProductionQuantity(1);
        productionEntity2.setProductionUnit(productionUnit2);
        productionEntity2.setRecipe(new ArrayList<>());
        productionEntity2.setUnitCost(10.0d);
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity2);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(stockService)
                .updateStockWhenProductChanged(Mockito.<ProductEntity>any(), Mockito.<ProductEntity>any(), anyInt(), anyInt());

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());
        product3.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit3 = new UnitEntity();
        productionUnit3.setCf(10.0d);
        productionUnit3.setId(1L);
        productionUnit3.setIngredientEntity(new ArrayList<>());
        productionUnit3.setName("Name");
        productionUnit3.setProductionEntity(new ArrayList<>());
        productionUnit3.setStockEntity(new ArrayList<>());

        ProductionEntity updatedProduction = new ProductionEntity();
        updatedProduction.setDamagedProduct(new ArrayList<>());
        updatedProduction.setDateOfProduction(mock(Date.class));
        updatedProduction.setId(1L);
        updatedProduction.setProduct(product3);
        updatedProduction.setProductionQuantity(1);
        updatedProduction.setProductionUnit(productionUnit3);
        updatedProduction.setRecipe(new ArrayList<>());
        updatedProduction.setUnitCost(10.0d);

        // Act
        productionServiceImp.updateProduction(updatedProduction);

        // Assert that nothing has changed
        verify(stockService).updateStockWhenProductChanged(isA(ProductEntity.class), isA(ProductEntity.class), eq(1),
                eq(1));
        verify(productionRepository).findById(eq(1L));
        verify(productionRepository).save(isA(ProductionEntity.class));
    }

    /**
     * Method under test: {@link ProductionServiceImp#deleteProduction(long)}
     */
    @Test
    public void testDeleteProduction() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        UnitEntity productionUnit = new UnitEntity();
        productionUnit.setCf(10.0d);
        productionUnit.setId(1L);
        productionUnit.setIngredientEntity(new ArrayList<>());
        productionUnit.setName("Name");
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setProduct(product);
        productionEntity.setProductionQuantity(1);
        productionEntity.setProductionUnit(productionUnit);
        productionEntity.setRecipe(new ArrayList<>());
        productionEntity.setUnitCost(10.0d);
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(productionRepository).deleteById(Mockito.<Long>any());

        // Act
        productionServiceImp.deleteProduction(1L);

        // Assert that nothing has changed
        verify(productionRepository).deleteById(eq(1L));
        verify(productionRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProductionServiceImp#deleteProduction(long)}
     */
    @Test
    public void testDeleteProduction2() {
        // Arrange
        Optional<ProductionEntity> emptyResult = Optional.empty();
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);
        doNothing().when(productionRepository).deleteById(Mockito.<Long>any());

        // Act
        productionServiceImp.deleteProduction(1L);

        // Assert that nothing has changed
        verify(productionRepository).deleteById(eq(1L));
        verify(productionRepository).findById(eq(1L));
    }
}
