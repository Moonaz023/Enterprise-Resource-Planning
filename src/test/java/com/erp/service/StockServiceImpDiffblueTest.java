package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;
import com.erp.entity.UnitEntity;
import com.erp.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {StockServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
public class StockServiceImpDiffblueTest {
    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImp stockServiceImp;

    /**
     * Method under test:
     * {@link StockServiceImp#updateStock(ProductEntity, int, UnitEntity)}
     */
    @Test
    public void testUpdateStock() {
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

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setProductionUnit(productionUnit);
        stockEntity.setStock_id(1L);

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

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setProductionUnit(productionUnit2);
        stockEntity2.setStock_id(1L);
        when(stockRepository.findByProductAndProductionUnit(Mockito.<ProductEntity>any(), Mockito.<UnitEntity>any()))
                .thenReturn(stockEntity);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());
        product3.setUnitPrice(new ArrayList<>());

        UnitEntity unit = new UnitEntity();
        unit.setCf(10.0d);
        unit.setId(1L);
        unit.setIngredientEntity(new ArrayList<>());
        unit.setName("Name");
        unit.setProductionEntity(new ArrayList<>());
        unit.setStockEntity(new ArrayList<>());

        // Act
        stockServiceImp.updateStock(product3, 1, unit);

        // Assert
        verify(stockRepository).findByProductAndProductionUnit(isA(ProductEntity.class), isA(UnitEntity.class));
        verify(stockRepository).save(isA(StockEntity.class));
    }

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockQuantity(ProductEntity, UnitEntity, int, int)}
     */
    @Test
    void testUpdateStockQuantity() {
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
        productionUnit.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setProductionUnit(productionUnit);
        stockEntity.setStock_id(1L);

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
        productionUnit2.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setProductionUnit(productionUnit2);
        stockEntity2.setStock_id(1L);
        when(stockRepository.findByProductAndProductionUnit(Mockito.<ProductEntity>any(), Mockito.<UnitEntity>any()))
                .thenReturn(stockEntity);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());
        product3.setUnitPrice(new ArrayList<>());

        UnitEntity unit = new UnitEntity();
        unit.setCf(10.0d);
        unit.setId(1L);
        unit.setIngredientEntity(new ArrayList<>());
        unit.setName("Name");
        unit.setProductBatchesStockEntity(new ArrayList<>());
        unit.setProductionEntity(new ArrayList<>());
        unit.setStockEntity(new ArrayList<>());

        // Act
        stockServiceImp.updateStockQuantity(product3, unit, 1, 1);

        // Assert
        verify(stockRepository).findByProductAndProductionUnit(isA(ProductEntity.class), isA(UnitEntity.class));
        verify(stockRepository).save(isA(StockEntity.class));
    }

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockWhenProductChanged(ProductEntity, UnitEntity, ProductEntity, UnitEntity, int, int)}
     */
    @Test
    void testUpdateStockWhenProductChanged() {
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
        productionUnit.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit.setProductionEntity(new ArrayList<>());
        productionUnit.setStockEntity(new ArrayList<>());

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setProductionUnit(productionUnit);
        stockEntity.setStock_id(1L);

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
        productionUnit2.setProductBatchesStockEntity(new ArrayList<>());
        productionUnit2.setProductionEntity(new ArrayList<>());
        productionUnit2.setStockEntity(new ArrayList<>());

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setProductionUnit(productionUnit2);
        stockEntity2.setStock_id(1L);
        when(stockRepository.findByProductAndProductionUnit(Mockito.<ProductEntity>any(), Mockito.<UnitEntity>any()))
                .thenReturn(stockEntity);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);

        ProductEntity oldProduct = new ProductEntity();
        oldProduct.setCategory("Category");
        oldProduct.setId(1L);
        oldProduct.setName("Name");
        oldProduct.setProductCode("Product Code");
        oldProduct.setProductions(new ArrayList<>());
        oldProduct.setUnitPrice(new ArrayList<>());

        UnitEntity oldUnit = new UnitEntity();
        oldUnit.setCf(10.0d);
        oldUnit.setId(1L);
        oldUnit.setIngredientEntity(new ArrayList<>());
        oldUnit.setName("Name");
        oldUnit.setProductBatchesStockEntity(new ArrayList<>());
        oldUnit.setProductionEntity(new ArrayList<>());
        oldUnit.setStockEntity(new ArrayList<>());

        ProductEntity newProduct = new ProductEntity();
        newProduct.setCategory("Category");
        newProduct.setId(1L);
        newProduct.setName("Name");
        newProduct.setProductCode("Product Code");
        newProduct.setProductions(new ArrayList<>());
        newProduct.setUnitPrice(new ArrayList<>());

        UnitEntity newUnit = new UnitEntity();
        newUnit.setCf(10.0d);
        newUnit.setId(1L);
        newUnit.setIngredientEntity(new ArrayList<>());
        newUnit.setName("Name");
        newUnit.setProductBatchesStockEntity(new ArrayList<>());
        newUnit.setProductionEntity(new ArrayList<>());
        newUnit.setStockEntity(new ArrayList<>());

        // Act
        stockServiceImp.updateStockWhenProductChanged(oldProduct, oldUnit, newProduct, newUnit, 1, 1);

        // Assert
        verify(stockRepository, atLeast(1)).findByProductAndProductionUnit(Mockito.<ProductEntity>any(),
                Mockito.<UnitEntity>any());
        verify(stockRepository, atLeast(1)).save(isA(StockEntity.class));
    }

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockQuantity(ProductEntity, int, int)}
     *
     * @Test public void testUpdateStockQuantity() { // Arrange ProductEntity
     *       product = new ProductEntity(); product.setCategory("Category");
     *       product.setId(1L); product.setName("Name");
     *       product.setProductCode("Product Code"); product.setProductions(new
     *       ArrayList<>()); product.setUnitPrice(new ArrayList<>());
     *
     *       UnitEntity productionUnit = new UnitEntity();
     *       productionUnit.setCf(10.0d); productionUnit.setId(1L);
     *       productionUnit.setIngredientEntity(new ArrayList<>());
     *       productionUnit.setName("Name"); productionUnit.setProductionEntity(new
     *       ArrayList<>()); productionUnit.setStockEntity(new ArrayList<>());
     *
     *       StockEntity stockEntity = new StockEntity();
     *       stockEntity.setProduct(product); stockEntity.setProductQuantity(1);
     *       stockEntity.setProductionUnit(productionUnit);
     *       stockEntity.setStock_id(1L);
     *
     *       ProductEntity product2 = new ProductEntity();
     *       product2.setCategory("Category"); product2.setId(1L);
     *       product2.setName("Name"); product2.setProductCode("Product Code");
     *       product2.setProductions(new ArrayList<>()); product2.setUnitPrice(new
     *       ArrayList<>());
     *
     *       UnitEntity productionUnit2 = new UnitEntity();
     *       productionUnit2.setCf(10.0d); productionUnit2.setId(1L);
     *       productionUnit2.setIngredientEntity(new ArrayList<>());
     *       productionUnit2.setName("Name");
     *       productionUnit2.setProductionEntity(new ArrayList<>());
     *       productionUnit2.setStockEntity(new ArrayList<>());
     *
     *       StockEntity stockEntity2 = new StockEntity();
     *       stockEntity2.setProduct(product2); stockEntity2.setProductQuantity(1);
     *       stockEntity2.setProductionUnit(productionUnit2);
     *       stockEntity2.setStock_id(1L);
     *       when(stockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(stockEntity);
     *       when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);
     *
     *       ProductEntity product3 = new ProductEntity();
     *       product3.setCategory("Category"); product3.setId(1L);
     *       product3.setName("Name"); product3.setProductCode("Product Code");
     *       product3.setProductions(new ArrayList<>()); product3.setUnitPrice(new
     *       ArrayList<>());
     *
     *       // Act stockServiceImp.updateStockQuantity(product3, 1, 1);
     *
     *       // Assert
     *       verify(stockRepository).findByProduct(isA(ProductEntity.class));
     *       verify(stockRepository).save(isA(StockEntity.class)); }
     */

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockWhenProductChanged(ProductEntity, ProductEntity, int, int)}
     *
     * @Test public void testUpdateStockWhenProductChanged() { // Arrange
     *       ProductEntity product = new ProductEntity();
     *       product.setCategory("Category"); product.setId(1L);
     *       product.setName("Name"); product.setProductCode("Product Code");
     *       product.setProductions(new ArrayList<>()); product.setUnitPrice(new
     *       ArrayList<>());
     *
     *       UnitEntity productionUnit = new UnitEntity();
     *       productionUnit.setCf(10.0d); productionUnit.setId(1L);
     *       productionUnit.setIngredientEntity(new ArrayList<>());
     *       productionUnit.setName("Name"); productionUnit.setProductionEntity(new
     *       ArrayList<>()); productionUnit.setStockEntity(new ArrayList<>());
     *
     *       StockEntity stockEntity = new StockEntity();
     *       stockEntity.setProduct(product); stockEntity.setProductQuantity(1);
     *       stockEntity.setProductionUnit(productionUnit);
     *       stockEntity.setStock_id(1L);
     *
     *       ProductEntity product2 = new ProductEntity();
     *       product2.setCategory("Category"); product2.setId(1L);
     *       product2.setName("Name"); product2.setProductCode("Product Code");
     *       product2.setProductions(new ArrayList<>()); product2.setUnitPrice(new
     *       ArrayList<>());
     *
     *       UnitEntity productionUnit2 = new UnitEntity();
     *       productionUnit2.setCf(10.0d); productionUnit2.setId(1L);
     *       productionUnit2.setIngredientEntity(new ArrayList<>());
     *       productionUnit2.setName("Name");
     *       productionUnit2.setProductionEntity(new ArrayList<>());
     *       productionUnit2.setStockEntity(new ArrayList<>());
     *
     *       StockEntity stockEntity2 = new StockEntity();
     *       stockEntity2.setProduct(product2); stockEntity2.setProductQuantity(1);
     *       stockEntity2.setProductionUnit(productionUnit2);
     *       stockEntity2.setStock_id(1L);
     *       when(stockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(stockEntity);
     *       when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);
     *
     *       ProductEntity oldProduct = new ProductEntity();
     *       oldProduct.setCategory("Category"); oldProduct.setId(1L);
     *       oldProduct.setName("Name"); oldProduct.setProductCode("Product Code");
     *       oldProduct.setProductions(new ArrayList<>());
     *       oldProduct.setUnitPrice(new ArrayList<>());
     *
     *       ProductEntity newProduct = new ProductEntity();
     *       newProduct.setCategory("Category"); newProduct.setId(1L);
     *       newProduct.setName("Name"); newProduct.setProductCode("Product Code");
     *       newProduct.setProductions(new ArrayList<>());
     *       newProduct.setUnitPrice(new ArrayList<>());
     *
     *       // Act stockServiceImp.updateStockWhenProductChanged(oldProduct,
     *       newProduct, 1, 1);
     *
     *       // Assert verify(stockRepository,
     *       atLeast(1)).findByProduct(Mockito.<ProductEntity>any());
     *       verify(stockRepository, atLeast(1)).save(isA(StockEntity.class)); }
     */
    /**
     * Method under test:
     * {@link StockServiceImp#updateStockWhenProductionDeteted(ProductEntity, UnitEntity, int)}
     */
    @Test
    public void testUpdateStockWhenProductionDeteted() {
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

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setProductionUnit(productionUnit);
        stockEntity.setStock_id(1L);

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

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setProductionUnit(productionUnit2);
        stockEntity2.setStock_id(1L);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);
        when(stockRepository.findByProductAndProductionUnit(Mockito.<ProductEntity>any(), Mockito.<UnitEntity>any()))
                .thenReturn(stockEntity);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());
        product3.setUnitPrice(new ArrayList<>());

        UnitEntity unit = new UnitEntity();
        unit.setCf(10.0d);
        unit.setId(1L);
        unit.setIngredientEntity(new ArrayList<>());
        unit.setName("Name");
        unit.setProductionEntity(new ArrayList<>());
        unit.setStockEntity(new ArrayList<>());

        // Act
        stockServiceImp.updateStockWhenProductionDeteted(product3, unit, 1);

        // Assert
        verify(stockRepository).findByProductAndProductionUnit(isA(ProductEntity.class), isA(UnitEntity.class));
        verify(stockRepository).save(isA(StockEntity.class));
    }

    /**
     * Method under test: {@link StockServiceImp#getAllProductsStock()}
     */
    @Test
    public void testGetAllProductsStock() {
        // Arrange
        ArrayList<StockEntity> stockEntityList = new ArrayList<>();
        when(stockRepository.findAll()).thenReturn(stockEntityList);

        // Act
        List<StockEntity> actualAllProductsStock = stockServiceImp.getAllProductsStock();

        // Assert
        verify(stockRepository).findAll();
        assertTrue(actualAllProductsStock.isEmpty());
        assertSame(stockEntityList, actualAllProductsStock);
    }
}
