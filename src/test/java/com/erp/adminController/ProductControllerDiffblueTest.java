package com.erp.adminController;
import static org.mockito.Mockito.when;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.erp.entity.ProductEntity;
import com.erp.service.ProductService;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.doNothing;
import jakarta.servlet.http.HttpSession;

import java.util.ArrayList;



import org.mockito.Mockito;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.ui.Model;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerDiffblueTest {
    @Autowired
    private ProductController productController;

    @MockBean
    private ProductService productService;

    /**
     * Method under test:
     * {@link ProductController#getAllproduct(Model, HttpSession)}
     */
    @Test
    public void testGetAllproduct() throws Exception {
        // Arrange
        when(productService.getAllproduct()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getAllProducts");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link ProductController#saveProduct(ProductEntity)}
     */
    @Test
    public void testSaveProduct() throws Exception {
        // Arrange
        doNothing().when(productService).saveProduct(Mockito.<ProductEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveProduct");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test: {@link ProductController#saveProduct(ProductEntity)}
     */
    @Test
    public void testSaveProduct2() throws Exception {
        // Arrange
        doNothing().when(productService).saveProduct(Mockito.<ProductEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/saveProduct");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isFound())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("redirect:/"))
                .andExpect(MockMvcResultMatchers.redirectedUrl("/"));
    }

    /**
     * Method under test:
     * {@link ProductController#updateProduct(ProductEntity, HttpSession)}
     */
    @Test
    public void testUpdateProduct() throws Exception {
        // Arrange
        doNothing().when(productService).updateProduct(Mockito.<ProductEntity>any());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/admin/updateProduct");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product record updated successfully"));
    }

    /**
     * Method under test: {@link ProductController#deleteProduct(long)}
     */
    @Test
    public void testDeleteProduct() throws Exception {
        // Arrange
        doNothing().when(productService).deleteProduct(anyLong());
        MockHttpServletRequestBuilder deleteResult = MockMvcRequestBuilders.delete("/admin/deleteProduct");
        MockHttpServletRequestBuilder requestBuilder = deleteResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Product deleted successfully"));
    }

    /**
     * Method under test: {@link ProductController#getProductById(long)}
     */
    @Test
    public void testGetProductById() throws Exception {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        productEntity.setUnitPrice(new ArrayList<>());
        when(productService.getProductById(anyLong())).thenReturn(productEntity);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/getProductById");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content()
                        .string(
                                "{\"id\":1,\"productCode\":\"Product Code\",\"name\":\"Name\",\"category\":\"Category\",\"unitPrice\":[]}"));
    }

    
    
   
    /**
     * Method under test: {@link ProductController#index()}
     */
    @Test
    public void testIndex() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Product"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Product"));
    }

    /**
     * Method under test: {@link ProductController#index()}
     */
    @Test
    public void testIndex2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(productController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Product"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Product"));
    }
}
