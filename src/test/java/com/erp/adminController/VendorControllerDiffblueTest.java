package com.erp.adminController;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import com.erp.entity.VendorEntity;
import com.erp.service.VendorService;
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

@ContextConfiguration(classes = {VendorController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class VendorControllerDiffblueTest {
    @Autowired
    private VendorController vendorController;

    @MockBean
    private VendorService vendorService;

    /**
     * Method under test: {@link VendorController#saveVendor(VendorEntity)}
     */
    @Test
    void testSaveVendor() throws Exception {
        // Arrange
        doNothing().when(vendorService).saveVendor(Mockito.<VendorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveVendor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vendor saved"));
    }

    /**
     * Method under test: {@link VendorController#saveVendor(VendorEntity)}
     */
    @Test
    void testSaveVendor2() throws Exception {
        // Arrange
        doNothing().when(vendorService).saveVendor(Mockito.<VendorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveVendor");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vendor saved"));
    }

    /**
     * Method under test: {@link VendorController#deleteVendor(long)}
     */
    @Test
    void testDeleteVendor() throws Exception {
        // Arrange
        doNothing().when(vendorService).deleteVendor(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/admin/deleteVendor");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vendor deleted successfully"));
    }

    /**
     * Method under test:
     * {@link VendorController#updateVendor(VendorEntity, HttpSession)}
     */
    @Test
    void testUpdateVendor() throws Exception {
        // Arrange
        doNothing().when(vendorService).updateVendor(Mockito.<VendorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateVendor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vendor record updated successfully"));
    }

    /**
     * Method under test:
     * {@link VendorController#updateVendor(VendorEntity, HttpSession)}
     */
    @Test
    void testUpdateVendor2() throws Exception {
        // Arrange
        doNothing().when(vendorService).updateVendor(Mockito.<VendorEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateVendor");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Vendor record updated successfully"));
    }

    /**
     * Method under test: {@link VendorController#getAllVendors(Model, HttpSession)}
     */
    @Test
    void testGetAllVendors() throws Exception {
        // Arrange
        when(vendorService.getAllVendor()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllVendors");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link VendorController#VendorIndex()}
     */
    @Test
    void testVendorIndex() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vendor");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Vendors"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Vendors"));
    }

    /**
     * Method under test: {@link VendorController#VendorIndex()}
     */
    @Test
    void testVendorIndex2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/vendor");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(vendorController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Vendors"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Vendors"));
    }
}
