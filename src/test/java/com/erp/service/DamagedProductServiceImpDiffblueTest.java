package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.DamagedProductEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.ProductionEntity;
import com.erp.repository.DamagedProductRepository;
import com.erp.repository.ProductBatchesStockRepository;

import java.sql.Date;
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

@ContextConfiguration(classes = {DamagedProductServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DamagedProductServiceImpDiffblueTest {
    @MockBean
    private DamagedProductRepository damagedProductRepository;

    @Autowired
    private DamagedProductServiceImp damagedProductServiceImp;

    @MockBean
    private ProductBatchesStockRepository productBatchesStockRepository;

    @MockBean
    private StockService stockService;

    /**
     * Method under test:
     * {@link DamagedProductServiceImp#save(DamagedProductEntity)}
     */
    @Test
    void testSave() {
        // Arrange
        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setPrice(10.0d);
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());

        ProductionEntity productionId = new ProductionEntity();
        productionId.setDamagedProduct(new ArrayList<>());
        productionId.setDateOfProduction(mock(Date.class));
        productionId.setId(1L);
        productionId.setMargin(10.0d);
        productionId.setProduct(product);
        productionId.setProductBatchesStockEntity(new ArrayList<>());
        productionId.setProductionQuantity(1);
        productionId.setRecipe(new ArrayList<>());

        DamagedProductEntity damagedProductEntity = new DamagedProductEntity();
        damagedProductEntity.setId(1L);
        damagedProductEntity.setProductionId(productionId);
        damagedProductEntity.setQuantity(1);
        when(damagedProductRepository.save(Mockito.<DamagedProductEntity>any())).thenReturn(damagedProductEntity);
        doNothing().when(productBatchesStockRepository).modifyStockByProduction(Mockito.<ProductionEntity>any(), anyInt());
        doNothing().when(stockService).updateStockWhenProductionDeteted(Mockito.<ProductEntity>any(), anyInt());

        ProductEntity product2 = new ProductEntity();
        product2.setCategory("Category");
        product2.setId(1L);
        product2.setName("Name");
        product2.setPrice(10.0d);
        product2.setProductCode("Product Code");
        product2.setProductions(new ArrayList<>());

        ProductionEntity productionId2 = new ProductionEntity();
        productionId2.setDamagedProduct(new ArrayList<>());
        productionId2.setDateOfProduction(mock(Date.class));
        productionId2.setId(1L);
        productionId2.setMargin(10.0d);
        productionId2.setProduct(product2);
        productionId2.setProductBatchesStockEntity(new ArrayList<>());
        productionId2.setProductionQuantity(1);
        productionId2.setRecipe(new ArrayList<>());

        DamagedProductEntity damagedProduct = new DamagedProductEntity();
        damagedProduct.setId(1L);
        damagedProduct.setProductionId(productionId2);
        damagedProduct.setQuantity(1);

        // Act
        damagedProductServiceImp.save(damagedProduct);

        // Assert that nothing has changed
        verify(productBatchesStockRepository).modifyStockByProduction(isA(ProductionEntity.class), eq(1));
        verify(stockService).updateStockWhenProductionDeteted(isA(ProductEntity.class), eq(1));
        verify(damagedProductRepository).save(isA(DamagedProductEntity.class));
    }

    /**
     * Method under test: {@link DamagedProductServiceImp#findAll()}
     */
    @Test
    void testFindAll() {
        // Arrange
        ArrayList<DamagedProductEntity> damagedProductEntityList = new ArrayList<>();
        when(damagedProductRepository.findAll()).thenReturn(damagedProductEntityList);

        // Act
        List<DamagedProductEntity> actualFindAllResult = damagedProductServiceImp.findAll();

        // Assert
        verify(damagedProductRepository).findAll();
        assertTrue(actualFindAllResult.isEmpty());
        assertSame(damagedProductEntityList, actualFindAllResult);
    }
}
