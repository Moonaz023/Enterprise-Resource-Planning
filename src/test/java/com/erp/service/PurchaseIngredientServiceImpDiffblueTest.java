package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.IngredientEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.entity.VendorEntity;
import com.erp.repository.PurchaseIngredientRepository;

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

@ContextConfiguration(classes = {PurchaseIngredientServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PurchaseIngredientServiceImpDiffblueTest {
    @MockBean
    private IngredientStockService ingredientStockService;

    @MockBean
    private PurchaseIngredientRepository purchaseIngredientRepository;

    @Autowired
    private PurchaseIngredientServiceImp purchaseIngredientServiceImp;

    /**
     * Method under test:
     * {@link PurchaseIngredientServiceImp#savePurchasedIngredient(PurchaseIngredientEntity)}
     */
    @Test
    void testSavePurchasedIngredient() {
        // Arrange
        doNothing().when(ingredientStockService).saveIngredientStock(Mockito.<PurchaseIngredientEntity>any());

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseIngredientEntity = new PurchaseIngredientEntity();
        purchaseIngredientEntity.setBill(10.0d);
        purchaseIngredientEntity.setDateOfPurchase(mock(Date.class));
        purchaseIngredientEntity.setId(1L);
        purchaseIngredientEntity.setIngredient(ingredient);
        purchaseIngredientEntity.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseIngredientEntity.setPaid(10.0d);
        purchaseIngredientEntity.setQuantity(10.0d);
        purchaseIngredientEntity.setVendor(vendor);
        when(purchaseIngredientRepository.save(Mockito.<PurchaseIngredientEntity>any()))
                .thenReturn(purchaseIngredientEntity);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor2 = new VendorEntity();
        vendor2.setAddress("42 Main St");
        vendor2.setEmail("jane.doe@example.org");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setPhone("6625550144");

        PurchaseIngredientEntity purchasedIngredient = new PurchaseIngredientEntity();
        purchasedIngredient.setBill(10.0d);
        purchasedIngredient.setDateOfPurchase(mock(Date.class));
        purchasedIngredient.setId(1L);
        purchasedIngredient.setIngredient(ingredient2);
        purchasedIngredient.setIngredientBatchesStockEntity(new ArrayList<>());
        purchasedIngredient.setPaid(10.0d);
        purchasedIngredient.setQuantity(10.0d);
        purchasedIngredient.setVendor(vendor2);

        // Act
        purchaseIngredientServiceImp.savePurchasedIngredient(purchasedIngredient);

        // Assert that nothing has changed
        verify(ingredientStockService).saveIngredientStock(isA(PurchaseIngredientEntity.class));
        verify(purchaseIngredientRepository).save(isA(PurchaseIngredientEntity.class));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientServiceImp#getAllPurchasedIngredients()}
     */
    @Test
    void testGetAllPurchasedIngredients() {
        // Arrange
        ArrayList<PurchaseIngredientEntity> purchaseIngredientEntityList = new ArrayList<>();
        when(purchaseIngredientRepository.findAll()).thenReturn(purchaseIngredientEntityList);

        // Act
        List<PurchaseIngredientEntity> actualAllPurchasedIngredients = purchaseIngredientServiceImp
                .getAllPurchasedIngredients();

        // Assert
        verify(purchaseIngredientRepository).findAll();
        assertTrue(actualAllPurchasedIngredients.isEmpty());
        assertSame(purchaseIngredientEntityList, actualAllPurchasedIngredients);
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientServiceImp#deleteIngredientPurchaseRecord(Long)}
     */
    @Test
    void testDeleteIngredientPurchaseRecord() {
        // Arrange
        when(ingredientStockService.modifystock_purchagedlt(Mockito.<IngredientEntity>any(), anyDouble()))
                .thenReturn(10.0d);

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseIngredientEntity = new PurchaseIngredientEntity();
        purchaseIngredientEntity.setBill(10.0d);
        purchaseIngredientEntity.setDateOfPurchase(mock(Date.class));
        purchaseIngredientEntity.setId(1L);
        purchaseIngredientEntity.setIngredient(ingredient);
        purchaseIngredientEntity.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseIngredientEntity.setPaid(10.0d);
        purchaseIngredientEntity.setQuantity(10.0d);
        purchaseIngredientEntity.setVendor(vendor);
        Optional<PurchaseIngredientEntity> ofResult = Optional.of(purchaseIngredientEntity);
        doNothing().when(purchaseIngredientRepository).deleteById(Mockito.<Long>any());
        when(purchaseIngredientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        purchaseIngredientServiceImp.deleteIngredientPurchaseRecord(1L);

        // Assert that nothing has changed
        verify(ingredientStockService).modifystock_purchagedlt(isA(IngredientEntity.class), eq(10.0d));
        verify(purchaseIngredientRepository).deleteById(eq(1L));
        verify(purchaseIngredientRepository).findById(eq(1L));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientServiceImp#updateIngredientPurchase(PurchaseIngredientEntity)}
     */
    @Test
    void testUpdateIngredientPurchase() {
        // Arrange
        when(ingredientStockService.modifystock_purchagedlt(Mockito.<IngredientEntity>any(), anyDouble()))
                .thenReturn(10.0d);
        doNothing().when(ingredientStockService).saveIngredientStock(Mockito.<PurchaseIngredientEntity>any());

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseIngredientEntity = new PurchaseIngredientEntity();
        purchaseIngredientEntity.setBill(10.0d);
        purchaseIngredientEntity.setDateOfPurchase(mock(Date.class));
        purchaseIngredientEntity.setId(1L);
        purchaseIngredientEntity.setIngredient(ingredient);
        purchaseIngredientEntity.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseIngredientEntity.setPaid(10.0d);
        purchaseIngredientEntity.setQuantity(10.0d);
        purchaseIngredientEntity.setVendor(vendor);
        Optional<PurchaseIngredientEntity> ofResult = Optional.of(purchaseIngredientEntity);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor2 = new VendorEntity();
        vendor2.setAddress("42 Main St");
        vendor2.setEmail("jane.doe@example.org");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setPhone("6625550144");

        PurchaseIngredientEntity purchaseIngredientEntity2 = new PurchaseIngredientEntity();
        purchaseIngredientEntity2.setBill(10.0d);
        purchaseIngredientEntity2.setDateOfPurchase(mock(Date.class));
        purchaseIngredientEntity2.setId(1L);
        purchaseIngredientEntity2.setIngredient(ingredient2);
        purchaseIngredientEntity2.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseIngredientEntity2.setPaid(10.0d);
        purchaseIngredientEntity2.setQuantity(10.0d);
        purchaseIngredientEntity2.setVendor(vendor2);
        when(purchaseIngredientRepository.save(Mockito.<PurchaseIngredientEntity>any()))
                .thenReturn(purchaseIngredientEntity2);
        when(purchaseIngredientRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(1L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("Ingredient Code");
        ingredient3.setIngredientName("Ingredient Name");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor3 = new VendorEntity();
        vendor3.setAddress("42 Main St");
        vendor3.setEmail("jane.doe@example.org");
        vendor3.setId(1L);
        vendor3.setName("Name");
        vendor3.setPhone("6625550144");

        PurchaseIngredientEntity purchasedIngredient = new PurchaseIngredientEntity();
        purchasedIngredient.setBill(10.0d);
        purchasedIngredient.setDateOfPurchase(mock(Date.class));
        purchasedIngredient.setId(1L);
        purchasedIngredient.setIngredient(ingredient3);
        purchasedIngredient.setIngredientBatchesStockEntity(new ArrayList<>());
        purchasedIngredient.setPaid(10.0d);
        purchasedIngredient.setQuantity(10.0d);
        purchasedIngredient.setVendor(vendor3);

        // Act
        purchaseIngredientServiceImp.updateIngredientPurchase(purchasedIngredient);

        // Assert that nothing has changed
        verify(ingredientStockService).modifystock_purchagedlt(isA(IngredientEntity.class), eq(10.0d));
        verify(ingredientStockService).saveIngredientStock(isA(PurchaseIngredientEntity.class));
        verify(purchaseIngredientRepository).findById(eq(1L));
        verify(purchaseIngredientRepository).save(isA(PurchaseIngredientEntity.class));
    }
}
