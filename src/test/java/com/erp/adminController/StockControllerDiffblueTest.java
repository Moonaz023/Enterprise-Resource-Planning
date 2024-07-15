package com.erp.adminController;

import static org.mockito.Mockito.when;

import com.erp.service.StockService;
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

@ContextConfiguration(classes = {StockController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class StockControllerDiffblueTest {
    @Autowired
    private StockController stockController;

    @MockBean
    private StockService stockService;

    /**
     * Method under test:
     * {@link StockController#getAllProductsStock(Model, HttpSession)}
     */
    @Test
    void testGetAllProductsStock() throws Exception {
        // Arrange
        when(stockService.getAllProductsStock()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllProductsStock");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link StockController#stock()}
     */
    @Test
    void testStock() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Stock"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Stock"));
    }

    /**
     * Method under test: {@link StockController#stock()}
     */
    @Test
    void testStock2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/stock");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(stockController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Stock"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Stock"));
    }
}
