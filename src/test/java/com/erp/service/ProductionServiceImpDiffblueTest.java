package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.ProductBatchesStockEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;
import com.erp.repository.ProductBatchesStockRepository;
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
class ProductionServiceImpDiffblueTest {
    @MockBean
    private IngredientStockService ingredientStockService;

    @MockBean
    private ProductBatchesStockRepository productBatchesStockRepository;

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
    void testSaveProduction() {
        // Arrange
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(true);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setMargin(10.0d);
        production.setProduct(product2);
        production.setProductBatchesStockEntity(new ArrayList<>());
        production.setProductionQuantity(1);
        production.setRecipe(new ArrayList<>());

        ProductBatchesStockEntity productBatchesStockEntity = new ProductBatchesStockEntity();
        productBatchesStockEntity.setCostPerUnit(10.0d);
        productBatchesStockEntity.setId(1L);
        productBatchesStockEntity.setProduct(product);
        productBatchesStockEntity.setProduction(production);
        productBatchesStockEntity.setQuantity(1);
        when(productBatchesStockRepository.save(Mockito.<ProductBatchesStockEntity>any()))
                .thenReturn(productBatchesStockEntity);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setPrice(10.0d);
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setMargin(10.0d);
        productionEntity.setProduct(product3);
        productionEntity.setProductBatchesStockEntity(new ArrayList<>());
        productionEntity.setProductionQuantity(1);
        productionEntity.setRecipe(new ArrayList<>());
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity);
        doNothing().when(stockService).updateStock(Mockito.<ProductEntity>any(), anyInt());

        ProductEntity product4 = new ProductEntity();
        product4.setCategory("Category");
        product4.setId(1L);
        product4.setName("Name");
        product4.setPrice(10.0d);
        product4.setProductCode("Product Code");
        product4.setProductions(new ArrayList<>());

        ProductionEntity production2 = new ProductionEntity();
        production2.setDamagedProduct(new ArrayList<>());
        production2.setDateOfProduction(mock(Date.class));
        production2.setId(1L);
        production2.setMargin(10.0d);
        production2.setProduct(product4);
        production2.setProductBatchesStockEntity(new ArrayList<>());
        production2.setProductionQuantity(1);
        production2.setRecipe(new ArrayList<>());

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production2);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        verify(stockService).updateStock(isA(ProductEntity.class), eq(1));
        verify(productBatchesStockRepository).save(isA(ProductBatchesStockEntity.class));
        verify(productionRepository).save(isA(ProductionEntity.class));
        assertEquals("ok", actualSaveProductionResult);
    }

    /**
     * Method under test:
     * {@link ProductionServiceImp#saveProduction(ProductionEntity)}
     */
    @Test
    void testSaveProduction2() {
        // Arrange
        when(ingredientStockService.checkAvailablity(Mockito.<List<RecipeDataDOT>>any())).thenReturn(false);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        ProductionEntity production = new ProductionEntity();
        production.setDamagedProduct(new ArrayList<>());
        production.setDateOfProduction(mock(Date.class));
        production.setId(1L);
        production.setMargin(10.0d);
        production.setProduct(product);
        production.setProductBatchesStockEntity(new ArrayList<>());
        production.setProductionQuantity(1);
        production.setRecipe(new ArrayList<>());

        // Act
        String actualSaveProductionResult = productionServiceImp.saveProduction(production);

        // Assert
        verify(ingredientStockService).checkAvailablity(isA(List.class));
        assertEquals("no", actualSaveProductionResult);
    }

    /**
     * Method under test: {@link ProductionServiceImp#getAllproduction()}
     */
    @Test
    void testGetAllproduction() {
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
    void testGetProductionById() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setMargin(10.0d);
        productionEntity.setProduct(product);
        productionEntity.setProductBatchesStockEntity(new ArrayList<>());
        productionEntity.setProductionQuantity(1);
        productionEntity.setRecipe(new ArrayList<>());
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
    void testUpdateProduction() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setMargin(10.0d);
        productionEntity.setProduct(product);
        productionEntity.setProductBatchesStockEntity(new ArrayList<>());
        productionEntity.setProductionQuantity(1);
        productionEntity.setRecipe(new ArrayList<>());
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        ProductionEntity productionEntity2 = new ProductionEntity();
        productionEntity2.setDamagedProduct(new ArrayList<>());
        productionEntity2.setDateOfProduction(mock(Date.class));
        productionEntity2.setId(1L);
        productionEntity2.setMargin(10.0d);
        productionEntity2.setProduct(product2);
        productionEntity2.setProductBatchesStockEntity(new ArrayList<>());
        productionEntity2.setProductionQuantity(1);
        productionEntity2.setRecipe(new ArrayList<>());
        when(productionRepository.save(Mockito.<ProductionEntity>any())).thenReturn(productionEntity2);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(stockService)
                .updateStockWhenProductChanged(Mockito.<ProductEntity>any(), Mockito.<ProductEntity>any(), anyInt(), anyInt());

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setPrice(10.0d);
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());

        ProductionEntity updatedProduction = new ProductionEntity();
        updatedProduction.setDamagedProduct(new ArrayList<>());
        updatedProduction.setDateOfProduction(mock(Date.class));
        updatedProduction.setId(1L);
        updatedProduction.setMargin(10.0d);
        updatedProduction.setProduct(product3);
        updatedProduction.setProductBatchesStockEntity(new ArrayList<>());
        updatedProduction.setProductionQuantity(1);
        updatedProduction.setRecipe(new ArrayList<>());

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
    void testDeleteProduction() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        ProductionEntity productionEntity = new ProductionEntity();
        productionEntity.setDamagedProduct(new ArrayList<>());
        productionEntity.setDateOfProduction(mock(Date.class));
        productionEntity.setId(1L);
        productionEntity.setMargin(10.0d);
        productionEntity.setProduct(product);
        productionEntity.setProductBatchesStockEntity(new ArrayList<>());
        productionEntity.setProductionQuantity(1);
        productionEntity.setRecipe(new ArrayList<>());
        Optional<ProductionEntity> ofResult = Optional.of(productionEntity);
        when(productionRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        doNothing().when(productionRepository).deleteById(Mockito.<Long>any());
        doNothing().when(stockService).updateStockWhenProductionDeteted(Mockito.<ProductEntity>any(), anyInt());

        // Act
        productionServiceImp.deleteProduction(1L);

        // Assert that nothing has changed
        verify(stockService).updateStockWhenProductionDeteted(isA(ProductEntity.class), eq(1));
        verify(productionRepository).deleteById(eq(1L));
        verify(productionRepository).findById(eq(1L));
    }

    /**
     * Method under test: {@link ProductionServiceImp#deleteProduction(long)}
     */
    @Test
    void testDeleteProduction2() {
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
