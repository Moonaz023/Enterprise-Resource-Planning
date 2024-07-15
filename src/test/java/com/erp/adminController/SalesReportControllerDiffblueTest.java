package com.erp.adminController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import com.erp.service.SalesReportService;
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

@ContextConfiguration(classes = {SalesReportController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SalesReportControllerDiffblueTest {
    @Autowired
    private SalesReportController salesReportController;

    @MockBean
    private SalesReportService salesReportService;

    /**
     * Method under test: {@link SalesReportController#salesReport()}
     */
    @Test
    void testSalesReport() {
        //   Diffblue Cover was unable to create a Spring-specific test for this Spring method.

        // Arrange, Act and Assert
        assertEquals("SalesReport", (new SalesReportController()).salesReport());
    }

    /**
     * Method under test:
     * {@link SalesReportController#getAllSales(Model, HttpSession)}
     */
    @Test
    void testGetAllSales() throws Exception {
        // Arrange
        when(salesReportService.getAllSales()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllSales");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(salesReportController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
