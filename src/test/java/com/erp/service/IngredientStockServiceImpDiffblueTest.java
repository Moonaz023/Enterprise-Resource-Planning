package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyDouble;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.RecipeDataDOT;
import com.erp.entity.IngredientBatchesStockEntity;
import com.erp.entity.IngredientEntity;
import com.erp.entity.IngredientStockEntity;
import com.erp.entity.PurchaseIngredientEntity;
import com.erp.entity.VendorEntity;
import com.erp.repository.IngredientBatchesStockRepository;
import com.erp.repository.IngredientStockRepository;

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

@ContextConfiguration(classes = {IngredientStockServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class IngredientStockServiceImpDiffblueTest {
    @MockBean
    private IngredientBatchesStockRepository ingredientBatchesStockRepository;

    @MockBean
    private IngredientStockRepository ingredientStockRepository;

    @Autowired
    private IngredientStockServiceImp ingredientStockServiceImp;

    /**
     * Method under test:
     * {@link IngredientStockServiceImp#saveIngredientStock(PurchaseIngredientEntity)}
     */
    @Test
    void testSaveIngredientStock() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseId = new PurchaseIngredientEntity();
        purchaseId.setBill(10.0d);
        purchaseId.setDateOfPurchase(mock(Date.class));
        purchaseId.setId(1L);
        purchaseId.setIngredient(ingredient2);
        purchaseId.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseId.setPaid(10.0d);
        purchaseId.setQuantity(10.0d);
        purchaseId.setVendor(vendor);

        IngredientBatchesStockEntity ingredientBatchesStockEntity = new IngredientBatchesStockEntity();
        ingredientBatchesStockEntity.setId(1L);
        ingredientBatchesStockEntity.setIngredient(ingredient);
        ingredientBatchesStockEntity.setPurchaseId(purchaseId);
        ingredientBatchesStockEntity.setQuantity(10.0d);
        ingredientBatchesStockEntity.setUnitCost(10.0d);
        when(ingredientBatchesStockRepository.save(Mockito.<IngredientBatchesStockEntity>any()))
                .thenReturn(ingredientBatchesStockEntity);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(1L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("Ingredient Code");
        ingredient3.setIngredientName("Ingredient Name");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity = new IngredientStockEntity();
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient3);
        ingredientStockEntity.setIngredientQuantity(10.0d);

        IngredientEntity ingredient4 = new IngredientEntity();
        ingredient4.setId(1L);
        ingredient4.setIngredientBatchesStock(new ArrayList<>());
        ingredient4.setIngredientCode("Ingredient Code");
        ingredient4.setIngredientName("Ingredient Name");
        ingredient4.setIngredientStockEntity(new ArrayList<>());
        ingredient4.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity2 = new IngredientStockEntity();
        ingredientStockEntity2.setId(1L);
        ingredientStockEntity2.setIngredient(ingredient4);
        ingredientStockEntity2.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);
        when(ingredientStockRepository.save(Mockito.<IngredientStockEntity>any())).thenReturn(ingredientStockEntity2);

        IngredientEntity ingredient5 = new IngredientEntity();
        ingredient5.setId(1L);
        ingredient5.setIngredientBatchesStock(new ArrayList<>());
        ingredient5.setIngredientCode("Ingredient Code");
        ingredient5.setIngredientName("Ingredient Name");
        ingredient5.setIngredientStockEntity(new ArrayList<>());
        ingredient5.setPurchaseIngredientEntity(new ArrayList<>());

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
        purchasedIngredient.setIngredient(ingredient5);
        purchasedIngredient.setIngredientBatchesStockEntity(new ArrayList<>());
        purchasedIngredient.setPaid(10.0d);
        purchasedIngredient.setQuantity(10.0d);
        purchasedIngredient.setVendor(vendor2);

        // Act
        ingredientStockServiceImp.saveIngredientStock(purchasedIngredient);

        // Assert
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientBatchesStockRepository).save(isA(IngredientBatchesStockEntity.class));
        verify(ingredientStockRepository).save(isA(IngredientStockEntity.class));
    }

    /**
     * Method under test:
     * {@link IngredientStockServiceImp#modifystock_purchagedlt(IngredientEntity, double)}
     */
    @Test
    void testModifystock_purchagedlt() {
        // Arrange
        when(ingredientBatchesStockRepository.findByIngredient(Mockito.<IngredientEntity>any()))
                .thenReturn(new ArrayList<>());

        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity = new IngredientStockEntity();
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient);
        ingredientStockEntity.setIngredientQuantity(10.0d);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity2 = new IngredientStockEntity();
        ingredientStockEntity2.setId(1L);
        ingredientStockEntity2.setIngredient(ingredient2);
        ingredientStockEntity2.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.save(Mockito.<IngredientStockEntity>any())).thenReturn(ingredientStockEntity2);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(1L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("Ingredient Code");
        ingredient3.setIngredientName("Ingredient Name");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        // Act
        double actualModifystock_purchagedltResult = ingredientStockServiceImp.modifystock_purchagedlt(ingredient3, 10.0d);

        // Assert
        verify(ingredientBatchesStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientStockRepository).save(isA(IngredientStockEntity.class));
        assertEquals(0.0d, actualModifystock_purchagedltResult);
    }

    /**
     * Method under test:
     * {@link IngredientStockServiceImp#modifystock_purchagedlt(IngredientEntity, double)}
     */
    @Test
    void testModifystock_purchagedlt2() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseId = new PurchaseIngredientEntity();
        purchaseId.setBill(10.0d);
        purchaseId.setDateOfPurchase(mock(Date.class));
        purchaseId.setId(1L);
        purchaseId.setIngredient(ingredient2);
        purchaseId.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseId.setPaid(10.0d);
        purchaseId.setQuantity(10.0d);
        purchaseId.setVendor(vendor);

        IngredientBatchesStockEntity ingredientBatchesStockEntity = new IngredientBatchesStockEntity();
        ingredientBatchesStockEntity.setId(1L);
        ingredientBatchesStockEntity.setIngredient(ingredient);
        ingredientBatchesStockEntity.setPurchaseId(purchaseId);
        ingredientBatchesStockEntity.setQuantity(10.0d);
        ingredientBatchesStockEntity.setUnitCost(10.0d);

        ArrayList<IngredientBatchesStockEntity> ingredientBatchesStockEntityList = new ArrayList<>();
        ingredientBatchesStockEntityList.add(ingredientBatchesStockEntity);
        doNothing().when(ingredientBatchesStockRepository).delete(Mockito.<IngredientBatchesStockEntity>any());
        when(ingredientBatchesStockRepository.findByIngredient(Mockito.<IngredientEntity>any()))
                .thenReturn(ingredientBatchesStockEntityList);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(1L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("Ingredient Code");
        ingredient3.setIngredientName("Ingredient Name");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity = new IngredientStockEntity();
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient3);
        ingredientStockEntity.setIngredientQuantity(10.0d);

        IngredientEntity ingredient4 = new IngredientEntity();
        ingredient4.setId(1L);
        ingredient4.setIngredientBatchesStock(new ArrayList<>());
        ingredient4.setIngredientCode("Ingredient Code");
        ingredient4.setIngredientName("Ingredient Name");
        ingredient4.setIngredientStockEntity(new ArrayList<>());
        ingredient4.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity2 = new IngredientStockEntity();
        ingredientStockEntity2.setId(1L);
        ingredientStockEntity2.setIngredient(ingredient4);
        ingredientStockEntity2.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.save(Mockito.<IngredientStockEntity>any())).thenReturn(ingredientStockEntity2);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient5 = new IngredientEntity();
        ingredient5.setId(1L);
        ingredient5.setIngredientBatchesStock(new ArrayList<>());
        ingredient5.setIngredientCode("Ingredient Code");
        ingredient5.setIngredientName("Ingredient Name");
        ingredient5.setIngredientStockEntity(new ArrayList<>());
        ingredient5.setPurchaseIngredientEntity(new ArrayList<>());

        // Act
        double actualModifystock_purchagedltResult = ingredientStockServiceImp.modifystock_purchagedlt(ingredient5, 10.0d);

        // Assert
        verify(ingredientBatchesStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientBatchesStockRepository).delete(isA(IngredientBatchesStockEntity.class));
        verify(ingredientStockRepository).save(isA(IngredientStockEntity.class));
        assertEquals(100.0d, actualModifystock_purchagedltResult);
    }

    /**
     * Method under test:
     * {@link IngredientStockServiceImp#modifystock_purchagedlt(IngredientEntity, double)}
     */
    @Test
    void testModifystock_purchagedlt3() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseId = new PurchaseIngredientEntity();
        purchaseId.setBill(10.0d);
        purchaseId.setDateOfPurchase(mock(Date.class));
        purchaseId.setId(1L);
        purchaseId.setIngredient(ingredient2);
        purchaseId.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseId.setPaid(10.0d);
        purchaseId.setQuantity(10.0d);
        purchaseId.setVendor(vendor);

        IngredientBatchesStockEntity ingredientBatchesStockEntity = new IngredientBatchesStockEntity();
        ingredientBatchesStockEntity.setId(1L);
        ingredientBatchesStockEntity.setIngredient(ingredient);
        ingredientBatchesStockEntity.setPurchaseId(purchaseId);
        ingredientBatchesStockEntity.setQuantity(10.0d);
        ingredientBatchesStockEntity.setUnitCost(10.0d);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(2L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("IngredientEntity(id=");
        ingredient3.setIngredientName("IngredientEntity(id=");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientEntity ingredient4 = new IngredientEntity();
        ingredient4.setId(2L);
        ingredient4.setIngredientBatchesStock(new ArrayList<>());
        ingredient4.setIngredientCode("IngredientEntity(id=");
        ingredient4.setIngredientName("IngredientEntity(id=");
        ingredient4.setIngredientStockEntity(new ArrayList<>());
        ingredient4.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor2 = new VendorEntity();
        vendor2.setAddress("17 High St");
        vendor2.setEmail("john.smith@example.org");
        vendor2.setId(2L);
        vendor2.setName("VendorEntity(id=");
        vendor2.setPhone("8605550118");

        PurchaseIngredientEntity purchaseId2 = new PurchaseIngredientEntity();
        purchaseId2.setBill(0.0d);
        purchaseId2.setDateOfPurchase(mock(Date.class));
        purchaseId2.setId(2L);
        purchaseId2.setIngredient(ingredient4);
        purchaseId2.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseId2.setPaid(0.0d);
        purchaseId2.setQuantity(0.0d);
        purchaseId2.setVendor(vendor2);

        IngredientBatchesStockEntity ingredientBatchesStockEntity2 = new IngredientBatchesStockEntity();
        ingredientBatchesStockEntity2.setId(2L);
        ingredientBatchesStockEntity2.setIngredient(ingredient3);
        ingredientBatchesStockEntity2.setPurchaseId(purchaseId2);
        ingredientBatchesStockEntity2.setQuantity(0.0d);
        ingredientBatchesStockEntity2.setUnitCost(0.0d);

        ArrayList<IngredientBatchesStockEntity> ingredientBatchesStockEntityList = new ArrayList<>();
        ingredientBatchesStockEntityList.add(ingredientBatchesStockEntity2);
        ingredientBatchesStockEntityList.add(ingredientBatchesStockEntity);
        doNothing().when(ingredientBatchesStockRepository).delete(Mockito.<IngredientBatchesStockEntity>any());
        when(ingredientBatchesStockRepository.findByIngredient(Mockito.<IngredientEntity>any()))
                .thenReturn(ingredientBatchesStockEntityList);

        IngredientEntity ingredient5 = new IngredientEntity();
        ingredient5.setId(1L);
        ingredient5.setIngredientBatchesStock(new ArrayList<>());
        ingredient5.setIngredientCode("Ingredient Code");
        ingredient5.setIngredientName("Ingredient Name");
        ingredient5.setIngredientStockEntity(new ArrayList<>());
        ingredient5.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity = new IngredientStockEntity();
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient5);
        ingredientStockEntity.setIngredientQuantity(10.0d);

        IngredientEntity ingredient6 = new IngredientEntity();
        ingredient6.setId(1L);
        ingredient6.setIngredientBatchesStock(new ArrayList<>());
        ingredient6.setIngredientCode("Ingredient Code");
        ingredient6.setIngredientName("Ingredient Name");
        ingredient6.setIngredientStockEntity(new ArrayList<>());
        ingredient6.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity2 = new IngredientStockEntity();
        ingredientStockEntity2.setId(1L);
        ingredientStockEntity2.setIngredient(ingredient6);
        ingredientStockEntity2.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.save(Mockito.<IngredientStockEntity>any())).thenReturn(ingredientStockEntity2);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient7 = new IngredientEntity();
        ingredient7.setId(1L);
        ingredient7.setIngredientBatchesStock(new ArrayList<>());
        ingredient7.setIngredientCode("Ingredient Code");
        ingredient7.setIngredientName("Ingredient Name");
        ingredient7.setIngredientStockEntity(new ArrayList<>());
        ingredient7.setPurchaseIngredientEntity(new ArrayList<>());

        // Act
        double actualModifystock_purchagedltResult = ingredientStockServiceImp.modifystock_purchagedlt(ingredient7, 10.0d);

        // Assert
        verify(ingredientBatchesStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientBatchesStockRepository, atLeast(1)).delete(Mockito.<IngredientBatchesStockEntity>any());
        verify(ingredientStockRepository).save(isA(IngredientStockEntity.class));
        assertEquals(100.0d, actualModifystock_purchagedltResult);
    }

    /**
     * Method under test:
     * {@link IngredientStockServiceImp#modifystock_purchagedlt(IngredientEntity, double)}
     */
    @Test
    void testModifystock_purchagedlt4() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor = new VendorEntity();
        vendor.setAddress("42 Main St");
        vendor.setEmail("jane.doe@example.org");
        vendor.setId(1L);
        vendor.setName("Name");
        vendor.setPhone("6625550144");

        PurchaseIngredientEntity purchaseId = new PurchaseIngredientEntity();
        purchaseId.setBill(10.0d);
        purchaseId.setDateOfPurchase(mock(Date.class));
        purchaseId.setId(1L);
        purchaseId.setIngredient(ingredient2);
        purchaseId.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseId.setPaid(10.0d);
        purchaseId.setQuantity(10.0d);
        purchaseId.setVendor(vendor);
        IngredientBatchesStockEntity ingredientBatchesStockEntity = mock(IngredientBatchesStockEntity.class);
        when(ingredientBatchesStockEntity.getQuantity()).thenReturn(10.0d);
        when(ingredientBatchesStockEntity.getUnitCost()).thenReturn(10.0d);
        doNothing().when(ingredientBatchesStockEntity).setId(anyLong());
        doNothing().when(ingredientBatchesStockEntity).setIngredient(Mockito.<IngredientEntity>any());
        doNothing().when(ingredientBatchesStockEntity).setPurchaseId(Mockito.<PurchaseIngredientEntity>any());
        doNothing().when(ingredientBatchesStockEntity).setQuantity(anyDouble());
        doNothing().when(ingredientBatchesStockEntity).setUnitCost(anyDouble());
        ingredientBatchesStockEntity.setId(1L);
        ingredientBatchesStockEntity.setIngredient(ingredient);
        ingredientBatchesStockEntity.setPurchaseId(purchaseId);
        ingredientBatchesStockEntity.setQuantity(10.0d);
        ingredientBatchesStockEntity.setUnitCost(10.0d);

        ArrayList<IngredientBatchesStockEntity> ingredientBatchesStockEntityList = new ArrayList<>();
        ingredientBatchesStockEntityList.add(ingredientBatchesStockEntity);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(1L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("Ingredient Code");
        ingredient3.setIngredientName("Ingredient Name");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientEntity ingredient4 = new IngredientEntity();
        ingredient4.setId(1L);
        ingredient4.setIngredientBatchesStock(new ArrayList<>());
        ingredient4.setIngredientCode("Ingredient Code");
        ingredient4.setIngredientName("Ingredient Name");
        ingredient4.setIngredientStockEntity(new ArrayList<>());
        ingredient4.setPurchaseIngredientEntity(new ArrayList<>());

        VendorEntity vendor2 = new VendorEntity();
        vendor2.setAddress("42 Main St");
        vendor2.setEmail("jane.doe@example.org");
        vendor2.setId(1L);
        vendor2.setName("Name");
        vendor2.setPhone("6625550144");

        PurchaseIngredientEntity purchaseId2 = new PurchaseIngredientEntity();
        purchaseId2.setBill(10.0d);
        purchaseId2.setDateOfPurchase(mock(Date.class));
        purchaseId2.setId(1L);
        purchaseId2.setIngredient(ingredient4);
        purchaseId2.setIngredientBatchesStockEntity(new ArrayList<>());
        purchaseId2.setPaid(10.0d);
        purchaseId2.setQuantity(10.0d);
        purchaseId2.setVendor(vendor2);

        IngredientBatchesStockEntity ingredientBatchesStockEntity2 = new IngredientBatchesStockEntity();
        ingredientBatchesStockEntity2.setId(1L);
        ingredientBatchesStockEntity2.setIngredient(ingredient3);
        ingredientBatchesStockEntity2.setPurchaseId(purchaseId2);
        ingredientBatchesStockEntity2.setQuantity(10.0d);
        ingredientBatchesStockEntity2.setUnitCost(10.0d);
        when(ingredientBatchesStockRepository.save(Mockito.<IngredientBatchesStockEntity>any()))
                .thenReturn(ingredientBatchesStockEntity2);
        when(ingredientBatchesStockRepository.findByIngredient(Mockito.<IngredientEntity>any()))
                .thenReturn(ingredientBatchesStockEntityList);

        IngredientEntity ingredient5 = new IngredientEntity();
        ingredient5.setId(1L);
        ingredient5.setIngredientBatchesStock(new ArrayList<>());
        ingredient5.setIngredientCode("Ingredient Code");
        ingredient5.setIngredientName("Ingredient Name");
        ingredient5.setIngredientStockEntity(new ArrayList<>());
        ingredient5.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity = new IngredientStockEntity();
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient5);
        ingredientStockEntity.setIngredientQuantity(10.0d);

        IngredientEntity ingredient6 = new IngredientEntity();
        ingredient6.setId(1L);
        ingredient6.setIngredientBatchesStock(new ArrayList<>());
        ingredient6.setIngredientCode("Ingredient Code");
        ingredient6.setIngredientName("Ingredient Name");
        ingredient6.setIngredientStockEntity(new ArrayList<>());
        ingredient6.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity2 = new IngredientStockEntity();
        ingredientStockEntity2.setId(1L);
        ingredientStockEntity2.setIngredient(ingredient6);
        ingredientStockEntity2.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.save(Mockito.<IngredientStockEntity>any())).thenReturn(ingredientStockEntity2);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient7 = new IngredientEntity();
        ingredient7.setId(1L);
        ingredient7.setIngredientBatchesStock(new ArrayList<>());
        ingredient7.setIngredientCode("Ingredient Code");
        ingredient7.setIngredientName("Ingredient Name");
        ingredient7.setIngredientStockEntity(new ArrayList<>());
        ingredient7.setPurchaseIngredientEntity(new ArrayList<>());

        // Act
        double actualModifystock_purchagedltResult = ingredientStockServiceImp.modifystock_purchagedlt(ingredient7, 10.0d);

        // Assert
        verify(ingredientBatchesStockEntity, atLeast(1)).getQuantity();
        verify(ingredientBatchesStockEntity).getUnitCost();
        verify(ingredientBatchesStockEntity).setId(eq(1L));
        verify(ingredientBatchesStockEntity).setIngredient(isA(IngredientEntity.class));
        verify(ingredientBatchesStockEntity).setPurchaseId(isA(PurchaseIngredientEntity.class));
        verify(ingredientBatchesStockEntity, atLeast(1)).setQuantity(anyDouble());
        verify(ingredientBatchesStockEntity).setUnitCost(eq(10.0d));
        verify(ingredientBatchesStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        verify(ingredientBatchesStockRepository).save(isA(IngredientBatchesStockEntity.class));
        verify(ingredientStockRepository).save(isA(IngredientStockEntity.class));
        assertEquals(100.0d, actualModifystock_purchagedltResult);
    }

    /**
     * Method under test: {@link IngredientStockServiceImp#getAllIngredientsStock()}
     */
    @Test
    void testGetAllIngredientsStock() {
        // Arrange
        ArrayList<IngredientStockEntity> ingredientStockEntityList = new ArrayList<>();
        when(ingredientStockRepository.findAll()).thenReturn(ingredientStockEntityList);

        // Act
        List<IngredientStockEntity> actualAllIngredientsStock = ingredientStockServiceImp.getAllIngredientsStock();

        // Assert
        verify(ingredientStockRepository).findAll();
        assertTrue(actualAllIngredientsStock.isEmpty());
        assertSame(ingredientStockEntityList, actualAllIngredientsStock);
    }

    /**
     * Method under test: {@link IngredientStockServiceImp#checkAvailablity(List)}
     */
    @Test
    void testCheckAvailablity() {
        // Arrange, Act and Assert
        assertTrue(ingredientStockServiceImp.checkAvailablity(new ArrayList<>()));
    }

    /**
     * Method under test: {@link IngredientStockServiceImp#checkAvailablity(List)}
     */
    @Test
    void testCheckAvailablity2() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());

        IngredientStockEntity ingredientStockEntity = new IngredientStockEntity();
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient);
        ingredientStockEntity.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        RecipeDataDOT recipeDataDOT = new RecipeDataDOT();
        recipeDataDOT.setIngredient(ingredient2);
        recipeDataDOT.setIngredientQuantity(10.0d);

        ArrayList<RecipeDataDOT> recipe = new ArrayList<>();
        recipe.add(recipeDataDOT);

        // Act
        boolean actualCheckAvailablityResult = ingredientStockServiceImp.checkAvailablity(recipe);

        // Assert
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        assertTrue(actualCheckAvailablityResult);
    }

    /**
     * Method under test: {@link IngredientStockServiceImp#checkAvailablity(List)}
     */
    @Test
    void testCheckAvailablity3() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());
        IngredientStockEntity ingredientStockEntity = mock(IngredientStockEntity.class);
        when(ingredientStockEntity.getIngredientQuantity()).thenReturn(0.5d);
        doNothing().when(ingredientStockEntity).setId(Mockito.<Long>any());
        doNothing().when(ingredientStockEntity).setIngredient(Mockito.<IngredientEntity>any());
        doNothing().when(ingredientStockEntity).setIngredientQuantity(anyDouble());
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient);
        ingredientStockEntity.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        RecipeDataDOT recipeDataDOT = new RecipeDataDOT();
        recipeDataDOT.setIngredient(ingredient2);
        recipeDataDOT.setIngredientQuantity(10.0d);

        ArrayList<RecipeDataDOT> recipe = new ArrayList<>();
        recipe.add(recipeDataDOT);

        // Act
        boolean actualCheckAvailablityResult = ingredientStockServiceImp.checkAvailablity(recipe);

        // Assert
        verify(ingredientStockEntity).getIngredientQuantity();
        verify(ingredientStockEntity).setId(eq(1L));
        verify(ingredientStockEntity).setIngredient(isA(IngredientEntity.class));
        verify(ingredientStockEntity).setIngredientQuantity(eq(10.0d));
        verify(ingredientStockRepository).findByIngredient(isA(IngredientEntity.class));
        assertFalse(actualCheckAvailablityResult);
    }

    /**
     * Method under test: {@link IngredientStockServiceImp#checkAvailablity(List)}
     */
    @Test
    void testCheckAvailablity4() {
        // Arrange
        IngredientEntity ingredient = new IngredientEntity();
        ingredient.setId(1L);
        ingredient.setIngredientBatchesStock(new ArrayList<>());
        ingredient.setIngredientCode("Ingredient Code");
        ingredient.setIngredientName("Ingredient Name");
        ingredient.setIngredientStockEntity(new ArrayList<>());
        ingredient.setPurchaseIngredientEntity(new ArrayList<>());
        IngredientStockEntity ingredientStockEntity = mock(IngredientStockEntity.class);
        when(ingredientStockEntity.getIngredientQuantity()).thenReturn(10.0d);
        doNothing().when(ingredientStockEntity).setId(Mockito.<Long>any());
        doNothing().when(ingredientStockEntity).setIngredient(Mockito.<IngredientEntity>any());
        doNothing().when(ingredientStockEntity).setIngredientQuantity(anyDouble());
        ingredientStockEntity.setId(1L);
        ingredientStockEntity.setIngredient(ingredient);
        ingredientStockEntity.setIngredientQuantity(10.0d);
        when(ingredientStockRepository.findByIngredient(Mockito.<IngredientEntity>any())).thenReturn(ingredientStockEntity);

        IngredientEntity ingredient2 = new IngredientEntity();
        ingredient2.setId(1L);
        ingredient2.setIngredientBatchesStock(new ArrayList<>());
        ingredient2.setIngredientCode("Ingredient Code");
        ingredient2.setIngredientName("Ingredient Name");
        ingredient2.setIngredientStockEntity(new ArrayList<>());
        ingredient2.setPurchaseIngredientEntity(new ArrayList<>());

        RecipeDataDOT recipeDataDOT = new RecipeDataDOT();
        recipeDataDOT.setIngredient(ingredient2);
        recipeDataDOT.setIngredientQuantity(10.0d);

        IngredientEntity ingredient3 = new IngredientEntity();
        ingredient3.setId(2L);
        ingredient3.setIngredientBatchesStock(new ArrayList<>());
        ingredient3.setIngredientCode("IngredientEntity(id=");
        ingredient3.setIngredientName("IngredientEntity(id=");
        ingredient3.setIngredientStockEntity(new ArrayList<>());
        ingredient3.setPurchaseIngredientEntity(new ArrayList<>());

        RecipeDataDOT recipeDataDOT2 = new RecipeDataDOT();
        recipeDataDOT2.setIngredient(ingredient3);
        recipeDataDOT2.setIngredientQuantity(0.5d);

        ArrayList<RecipeDataDOT> recipe = new ArrayList<>();
        recipe.add(recipeDataDOT2);
        recipe.add(recipeDataDOT);

        // Act
        boolean actualCheckAvailablityResult = ingredientStockServiceImp.checkAvailablity(recipe);

        // Assert
        verify(ingredientStockEntity, atLeast(1)).getIngredientQuantity();
        verify(ingredientStockEntity).setId(eq(1L));
        verify(ingredientStockEntity).setIngredient(isA(IngredientEntity.class));
        verify(ingredientStockEntity).setIngredientQuantity(eq(10.0d));
        verify(ingredientStockRepository, atLeast(1)).findByIngredient(Mockito.<IngredientEntity>any());
        assertTrue(actualCheckAvailablityResult);
    }
}
