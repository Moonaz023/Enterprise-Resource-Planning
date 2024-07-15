package com.erp.adminController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.erp.entity.ProductionEntity;
import com.erp.service.ProductionService;
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

@ContextConfiguration(classes = {ProductionController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class ProductionControllerDiffblueTest {
    @Autowired
    private ProductionController productionController;

    @MockBean
    private ProductionService productionService;

    /**
     * Method under test: {@link ProductionController#index()}
     */
    @Test
    void testIndex() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertEquals("production", (new ProductionController()).index());
    }

    /**
     * Method under test:
     * {@link ProductionController#getAllproduction(Model, HttpSession)}
     */
    @Test
    void testGetAllproduction() throws Exception {
        // Arrange
        when(productionService.getAllproduction()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/admin/getAllProductions");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test:
     * {@link ProductionController#updateProduction(ProductionEntity, HttpSession)}
     */
    @Test
    void testUpdateProduction() throws Exception {
        // Arrange
        doNothing().when(productionService).updateProduction(Mockito.<ProductionEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateProduction");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Production record updated successfully"));
    }

    /**
     * Method under test:
     * {@link ProductionController#updateProduction(ProductionEntity, HttpSession)}
     */
    @Test
    void testUpdateProduction2() throws Exception {
        // Arrange
        doNothing().when(productionService).updateProduction(Mockito.<ProductionEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateProduction");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Production record updated successfully"));
    }

    /**
     * Method under test: {@link ProductionController#deleteProduction(long)}
     */
    @Test
    void testDeleteProduction() throws Exception {
        // Arrange
        doNothing().when(productionService).deleteProduction(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/admin/deleteProduction");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Production deleted successfully"));
    }

    /**
     * Method under test: {@link ProductionController#saveProduct(ProductionEntity)}
     */
    @Test
    void testSaveProduct() throws Exception {
        // Arrange
        when(productionService.saveProduction(Mockito.<ProductionEntity>any())).thenReturn("Save Production");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveProduction");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productionController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Save Production"));
    }
}
