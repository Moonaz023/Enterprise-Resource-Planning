package com.erp.adminController;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;
import com.erp.service.DistributorService;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.HashMap;

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

@ContextConfiguration(classes = {DistributorController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class DistributorControllerDiffblueTest {
    @Autowired
    private DistributorController distributorController;

    @MockBean
    private DistributorService distributorService;

    /**
     * Method under test: {@link DistributorController#deleteDistributor(long)}
     */
    @Test
    void testDeleteDistributor() throws Exception {
        // Arrange
        doNothing().when(distributorService).deleteDistributor(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/admin/deleteDistributor");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Distributor deleted successfully"));
    }

    /**
     * Method under test:
     * {@link DistributorController#updateDistributor(DistributorEntity, HttpSession)}
     */
    @Test
    void testUpdateDistributor() throws Exception {
        // Arrange
        doNothing().when(distributorService).updateDistributor(Mockito.<DistributorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateDistributor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Distributor record updated successfully"));
    }

    /**
     * Method under test:
     * {@link DistributorController#updateDistributor(DistributorEntity, HttpSession)}
     */
    @Test
    void testUpdateDistributor2() throws Exception {
        // Arrange
        doNothing().when(distributorService).updateDistributor(Mockito.<DistributorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateDistributor");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Distributor record updated successfully"));
    }

    /**
     * Method under test:
     * {@link DistributorController#distributorProfile(Long, Model)}
     */
    @Test
    void testDistributorProfile() throws Exception {
        // Arrange
        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);
        DistributorHistoryDTO.DistributorHistoryDTOBuilder distributorResult = DistributorHistoryDTO.builder()
                .distributor(distributor);
        DistributorHistoryDTO buildResult = distributorResult.productSale(new HashMap<>())
                .totalDue(10.0d)
                .totalPaid(10.0d)
                .build();
        when(distributorService.distributorProfile(Mockito.<Long>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/DistributorProfile/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(1))
                .andExpect(MockMvcResultMatchers.model().attributeExists("distributorHistory"))
                .andExpect(MockMvcResultMatchers.view().name("DistributorProfile"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("DistributorProfile"));
    }

    /**
     * Method under test: {@link DistributorController#DistributorHistory(Long)}
     */
    @Test
    void testDistributorHistory() throws Exception {
        // Arrange
        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);
        DistributorHistoryDTO.DistributorHistoryDTOBuilder distributorResult = DistributorHistoryDTO.builder()
                .distributor(distributor);
        DistributorHistoryDTO buildResult = distributorResult.productSale(new HashMap<>())
                .totalDue(10.0d)
                .totalPaid(10.0d)
                .build();
        when(distributorService.distributorProfile(Mockito.<Long>any())).thenReturn(buildResult);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/DistributorHistory/{id}", 1L);

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"distributor\":{\"id\":1,\"name\":\"Name\",\"address\":\"42 Main St\",\"phone\":\"6625550144\",\"email\":\"jane.doe"
                                        + "@example.org\",\"total_order\":1},\"productSale\":{},\"totalPaid\":10.0,\"totalDue\":10.0}"));
    }

    /**
     * Method under test:
     * {@link DistributorController#getAllDistributors(Model, HttpSession)}
     */
    @Test
    void testGetAllDistributors() throws Exception {
        // Arrange
        when(distributorService.getAlldistributor()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllDistributors");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link DistributorController#index()}
     */
    @Test
    void testIndex() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/distributor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Distributor"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Distributor"));
    }

    /**
     * Method under test: {@link DistributorController#index()}
     */
    @Test
    void testIndex2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/distributor");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Distributor"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Distributor"));
    }

    /**
     * Method under test:
     * {@link DistributorController#saveProduct(DistributorEntity)}
     */
    @Test
    void testSaveProduct() throws Exception {
        // Arrange
        doNothing().when(distributorService).saveDistributor(Mockito.<DistributorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveDistributor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test:
     * {@link DistributorController#saveProduct(DistributorEntity)}
     */
    @Test
    void testSaveProduct2() throws Exception {
        // Arrange
        doNothing().when(distributorService).saveDistributor(Mockito.<DistributorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveDistributor");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(distributorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }
}
