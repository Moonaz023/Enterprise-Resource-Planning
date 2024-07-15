package com.erp.adminController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.service.PurchaseIngredientService;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

@ContextConfiguration(classes = {PurchaseIngredientController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class PurchaseIngredientControllerDiffblueTest {
    @Autowired
    private PurchaseIngredientController purchaseIngredientController;

    @MockBean
    private PurchaseIngredientService purchaseIngredientService;

    /**
     * Method under test: {@link PurchaseIngredientController#purchaseIngredient()}
     */
    @Test
    void testPurchaseIngredient() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertEquals("purchaseIngredient", (new PurchaseIngredientController()).purchaseIngredient());
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientController#savePurchasedIngredient(PurchaseIngredientEntity)}
     */
    @Test
    void testSavePurchasedIngredient() throws Exception {
        // Arrange
        doNothing().when(purchaseIngredientService).savePurchasedIngredient(Mockito.<PurchaseIngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/savePurchasedIngredient");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(purchaseIngredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("purchased Ingredient saved"));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientController#savePurchasedIngredient(PurchaseIngredientEntity)}
     */
    @Test
    void testSavePurchasedIngredient2() throws Exception {
        // Arrange
        doNothing().when(purchaseIngredientService).savePurchasedIngredient(Mockito.<PurchaseIngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/savePurchasedIngredient");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(purchaseIngredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("purchased Ingredient saved"));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientController#getAllPurchasedIngredients(Model, HttpSession)}
     */
    @Test
    void testGetAllPurchasedIngredients() throws Exception {
        // Arrange
        when(purchaseIngredientService.getAllPurchasedIngredients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllPurchasedIngredients");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(purchaseIngredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientController#deleteIngredientPurchaseRecord(Long)}
     */
    @Test
    void testDeleteIngredientPurchaseRecord() throws Exception {
        // Arrange
        doNothing().when(purchaseIngredientService).deleteIngredientPurchaseRecord(Mockito.<Long>any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/deleteIngredientPurchaseRecord");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(purchaseIngredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Success"));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientController#updateIngredientPurchase(PurchaseIngredientEntity)}
     */
    @Test
    void testUpdateIngredientPurchase() throws Exception {
        // Arrange
        doNothing().when(purchaseIngredientService).updateIngredientPurchase(Mockito.<PurchaseIngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/updateIngredientPurchase");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(purchaseIngredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("purchased Ingredient updated"));
    }

    /**
     * Method under test:
     * {@link PurchaseIngredientController#updateIngredientPurchase(PurchaseIngredientEntity)}
     */
    @Test
    void testUpdateIngredientPurchase2() throws Exception {
        // Arrange
        doNothing().when(purchaseIngredientService).updateIngredientPurchase(Mockito.<PurchaseIngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/updateIngredientPurchase");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(purchaseIngredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("purchased Ingredient updated"));
    }
}
