package com.erp.adminController;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.erp.entity.DamagedProductEntity;
import com.erp.service.DamagedProductService;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@ContextConfiguration(classes = {DamagedProductController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode


class DamagedProductControllerDiffblueTest {
    @Autowired
    private DamagedProductController damagedProductController;

    @MockBean
    private DamagedProductService damagedProductService;

    /**
     * Method under test: {@link DamagedProductController#getAllDamagedProduct()}
     */
    @Test
    void testGetAllDamagedProduct() throws Exception {
        // Arrange
        when(damagedProductService.findAll()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllDamagedProduct");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(damagedProductController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DamagedProductController#index()}
     */
    @Test
    void testIndex() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/damagedProduct");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(damagedProductController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("DamagedProduct"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("DamagedProduct"));
    }

    /**
     * Method under test: {@link DamagedProductController#index()}
     */
    @Test
    void testIndex2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/damagedProduct");
        requestBuilder.contentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(damagedProductController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("DamagedProduct"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("DamagedProduct"));
    }

    /**
     * Method under test:
     * {@link DamagedProductController#saveDamagedProduct(DamagedProductEntity)}
     */
    @Test
    void testSaveDamagedProduct() throws Exception {
        // Arrange
        doNothing().when(damagedProductService).save(Mockito.<DamagedProductEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveDamagedProduct");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(damagedProductController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("ok"));
    }

    /**
     * Method under test:
     * {@link DamagedProductController#saveDamagedProduct(DamagedProductEntity)}
     */
    @Test
    void testSaveDamagedProduct2() throws Exception {
        // Arrange
        doNothing().when(damagedProductService).save(Mockito.<DamagedProductEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/saveDamagedProduct");
        requestBuilder.contentType(MediaType.APPLICATION_FORM_URLENCODED);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(damagedProductController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("ok"));
    }
}
