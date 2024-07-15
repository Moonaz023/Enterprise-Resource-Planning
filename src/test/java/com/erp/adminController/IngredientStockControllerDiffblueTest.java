package com.erp.adminController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.erp.service.IngredientStockService;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

@ContextConfiguration(classes = {IngredientStockController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class IngredientStockControllerDiffblueTest {
    @Autowired
    private IngredientStockController ingredientStockController;

    @MockBean
    private IngredientStockService ingredientStockService;

    /**
     * Method under test:
     * {@link IngredientStockController#getAllProductsStock(Model, HttpSession)}
     */
    @Test
    void testGetAllProductsStock() throws Exception {
        // Arrange
        when(ingredientStockService.getAllIngredientsStock()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllIngredientStock");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientStockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link IngredientStockController#ingredient_stock()}
     */
    @Test
    void testIngredient_stock() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertEquals("ingredientstock", (new IngredientStockController()).ingredient_stock());
    }
}
