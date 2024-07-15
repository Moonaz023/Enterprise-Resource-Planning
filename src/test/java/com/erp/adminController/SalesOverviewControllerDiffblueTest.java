package com.erp.adminController;

import static org.mockito.Mockito.when;

import com.erp.service.SalesOverviewService;

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

@ContextConfiguration(classes = {SalesOverviewController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class SalesOverviewControllerDiffblueTest {
    @Autowired
    private SalesOverviewController salesOverviewController;

    @MockBean
    private SalesOverviewService salesOverviewService;

    /**
     * Method under test: {@link SalesOverviewController#getSalesOverview()}
     */
    @Test
    void testGetSalesOverview() throws Exception {
        // Arrange
        when(salesOverviewService.getSalesOverview()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getSalesOverview");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(salesOverviewController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }
}
