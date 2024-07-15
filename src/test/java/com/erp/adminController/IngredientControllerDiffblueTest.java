package com.erp.adminController;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.erp.entity.IngredientEntity;
import com.erp.service.IngredientService;
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

@ContextConfiguration(classes = {IngredientController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class IngredientControllerDiffblueTest {
    @Autowired
    private IngredientController ingredientController;

    @MockBean
    private IngredientService ingredientService;

    /**
     * Method under test:
     * {@link IngredientController#saveIngredient(IngredientEntity)}
     */
    @Test
    void testSaveIngredient() throws Exception {
        // Arrange
        doNothing().when(ingredientService).saveIngredient(Mockito.<IngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addIngredient");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ingredient Saved"));
    }

    /**
     * Method under test:
     * {@link IngredientController#saveIngredient(IngredientEntity)}
     */
    @Test
    void testSaveIngredient2() throws Exception {
        // Arrange
        doNothing().when(ingredientService).saveIngredient(Mockito.<IngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addIngredient");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ingredient Saved"));
    }

    /**
     * Method under test:
     * {@link IngredientController#getAllIngredients(Model, HttpSession)}
     */
    @Test
    void testGetAllIngredients() throws Exception {
        // Arrange
        when(ingredientService.getAllIngredients()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllIngredients");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link IngredientController#updateIngredient(IngredientEntity)}
     */
    @Test
    void testUpdateIngredient() throws Exception {
        // Arrange
        doNothing().when(ingredientService).updateIngredient(Mockito.<IngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/updateIngredient");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ingredient Updated"));
    }

    /**
     * Method under test:
     * {@link IngredientController#updateIngredient(IngredientEntity)}
     */
    @Test
    void testUpdateIngredient2() throws Exception {
        // Arrange
        doNothing().when(ingredientService).updateIngredient(Mockito.<IngredientEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/updateIngredient");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Ingredient Updated"));
    }

    /**
     * Method under test: {@link IngredientController#deleteIngredient(Long)}
     */
    @Test
    void testDeleteIngredient() throws Exception {
        // Arrange
        doNothing().when(ingredientService).deleteIngredient(Mockito.<Long>any());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/deleteIngredient");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Delete successful"));
    }

    /**
     * Method under test: {@link IngredientController#Ingredients()}
     */
    @Test
    void testIngredients() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ingredients");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Ingredients"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Ingredients"));
    }

    /**
     * Method under test: {@link IngredientController#Ingredients()}
     */
    @Test
    void testIngredients2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/ingredients", "Uri Variables");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(ingredientController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Ingredients"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Ingredients"));
    }
}
