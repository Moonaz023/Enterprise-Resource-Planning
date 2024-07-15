package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.ProductEntity;
import com.erp.entity.StockEntity;
import com.erp.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {StockServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class StockServiceImpDiffblueTest {
    @MockBean
    private StockRepository stockRepository;

    @Autowired
    private StockServiceImp stockServiceImp;

    /**
     * Method under test: {@link StockServiceImp#updateStock(ProductEntity, int)}
     */
    @Test
    void testUpdateStock() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setStock_id(1L);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setStock_id(1L);
        when(stockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(stockEntity);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setPrice(10.0d);
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());

        // Act
        stockServiceImp.updateStock(product3, 1);

        // Assert
        verify(stockRepository).findByProduct(isA(ProductEntity.class));
        verify(stockRepository).save(isA(StockEntity.class));
    }

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockQuantity(ProductEntity, int, int)}
     */
    @Test
    void testUpdateStockQuantity() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setStock_id(1L);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setStock_id(1L);
        when(stockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(stockEntity);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setPrice(10.0d);
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());

        // Act
        stockServiceImp.updateStockQuantity(product3, 1, 1);

        // Assert
        verify(stockRepository).findByProduct(isA(ProductEntity.class));
        verify(stockRepository).save(isA(StockEntity.class));
    }

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockWhenProductChanged(ProductEntity, ProductEntity, int, int)}
     */
    @Test
    void testUpdateStockWhenProductChanged() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setStock_id(1L);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setStock_id(1L);
        when(stockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(stockEntity);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);

        ProductEntity oldProduct = new ProductEntity();
        oldProduct.setCategory("Category");
        oldProduct.setId(1L);
        oldProduct.setName("Name");
        oldProduct.setPrice(10.0d);
        oldProduct.setProductCode("Product Code");
        oldProduct.setProductions(new ArrayList<>());

        ProductEntity newProduct = new ProductEntity();
        newProduct.setCategory("Category");
        newProduct.setId(1L);
        newProduct.setName("Name");
        newProduct.setPrice(10.0d);
        newProduct.setProductCode("Product Code");
        newProduct.setProductions(new ArrayList<>());

        // Act
        stockServiceImp.updateStockWhenProductChanged(oldProduct, newProduct, 1, 1);

        // Assert
        verify(stockRepository, atLeast(1)).findByProduct(Mockito.<ProductEntity>any());
        verify(stockRepository, atLeast(1)).save(isA(StockEntity.class));
    }

    /**
     * Method under test:
     * {@link StockServiceImp#updateStockWhenProductionDeteted(ProductEntity, int)}
     */
    @Test
    void testUpdateStockWhenProductionDeteted() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        StockEntity stockEntity = new StockEntity();
        stockEntity.setProduct(product);
        stockEntity.setProductQuantity(1);
        stockEntity.setStock_id(1L);

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        StockEntity stockEntity2 = new StockEntity();
        stockEntity2.setProduct(product2);
        stockEntity2.setProductQuantity(1);
        stockEntity2.setStock_id(1L);
        when(stockRepository.save(Mockito.<StockEntity>any())).thenReturn(stockEntity2);
        when(stockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(stockEntity);

        ProductEntity product3 = new ProductEntity();
        product3.setCategory("Category");
        product3.setId(1L);
        product3.setName("Name");
        product3.setPrice(10.0d);
        product3.setProductCode("Product Code");
        product3.setProductions(new ArrayList<>());

        // Act
        stockServiceImp.updateStockWhenProductionDeteted(product3, 1);

        // Assert
        verify(stockRepository).findByProduct(isA(ProductEntity.class));
        verify(stockRepository).save(isA(StockEntity.class));
    }

    /**
     * Method under test: {@link StockServiceImp#getAllProductsStock()}
     */
    @Test
    void testGetAllProductsStock() {
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
