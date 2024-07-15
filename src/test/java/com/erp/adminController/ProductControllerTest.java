package com.erp.adminController;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.redirectedUrl;



import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.erp.entity.ProductEntity;
import com.erp.service.ProductService;
import com.erp.repository.ProductRepository;

@WebMvcTest(controllers = ProductController.class)
@ExtendWith(MockitoExtension.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductService productService;

    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testGetAllProducts() throws Exception {
        List<ProductEntity> productList = Arrays.asList(
            ProductEntity.builder()
                         .id(1)
                         .productCode("P001")
                         .name("Product1")
                         .category("Category1")
                         .price(100.0)
                         .build(),
            ProductEntity.builder()
                         .id(2)
                         .productCode("P002")
                         .name("Product2")
                         .category("Category2")
                         .price(150.0)
                         .build()
        );

        when(productService.getAllproduct()).thenReturn(productList);

        mockMvc.perform(get("/getAllProducts")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$[0].id").value(1))
               .andExpect(jsonPath("$[0].productCode").value("P001"))
               .andExpect(jsonPath("$[0].name").value("Product1"))
               .andExpect(jsonPath("$[0].category").value("Category1"))
               .andExpect(jsonPath("$[0].price").value(100.0))
               .andExpect(jsonPath("$[1].id").value(2))
               .andExpect(jsonPath("$[1].productCode").value("P002"))
               .andExpect(jsonPath("$[1].name").value("Product2"))
               .andExpect(jsonPath("$[1].category").value("Category2"))
               .andExpect(jsonPath("$[1].price").value(150.0));
    }

    @Test
    public void testGetAllProductsEmpty() throws Exception {
        when(productService.getAllproduct()).thenReturn(Arrays.asList());

        mockMvc.perform(get("/getAllProducts")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$").isEmpty());
    }

    @Test
    public void testGetAllProductsException() throws Exception {
        when(productService.getAllproduct()).thenThrow(new RuntimeException("Exception occurred"));

        mockMvc.perform(get("/getAllProducts")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isInternalServerError());
    }
    /*
    @Test
    public void testGetAllProductsException() throws Exception {
        when(productService.getAllproduct()).thenThrow(new RuntimeException("Exception occurred"));

        mockMvc.perform(get("/getAllProducts")
               .contentType(MediaType.APPLICATION_JSON))
               .andExpect(status().isInternalServerError())
               .andExpect(jsonPath("$").doesNotExist());
    }*/
    
    
    @Test
    public void testSaveProduct() throws Exception {
        
        ProductEntity product = ProductEntity.builder()
                                             .id(1)
                                             .productCode("P001")
                                             .name("Product1")
                                             .category("Category1")
                                             .price(100.0)
                                             .build();

        
        mockMvc.perform(post("/admin/saveProduct")
               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
               .param("id", String.valueOf(product.getId()))
               .param("productCode", product.getProductCode())
               .param("name", product.getName())
               .param("category", product.getCategory())
               .param("price", String.valueOf(product.getPrice())))
               .andExpect(status().is3xxRedirection())
               .andExpect(redirectedUrl("/"));

        
        verify(productService, times(1)).saveProduct(any(ProductEntity.class));
    }
    
    @Test
    public void testUpdateProduct() throws Exception {
        
        ProductEntity product = ProductEntity.builder()
                                             .id(1)
                                             .productCode("P001")
                                             .name("Product1")
                                             .category("Category1")
                                             .price(100.0)
                                             .build();

        
        mockMvc.perform(post("/admin/updateProduct")
               .contentType(MediaType.APPLICATION_FORM_URLENCODED)
               .param("id", String.valueOf(product.getId()))
               .param("productCode", product.getProductCode())
               .param("name", product.getName())
               .param("category", product.getCategory())
               .param("price", String.valueOf(product.getPrice())))
               .andExpect(status().isOk())  
               .andExpect(content().string("Product record updated successfully"));

        
        verify(productService, times(1)).updateProduct(any(ProductEntity.class));
    }
    
    @Test
    public void testDeleteProduct() throws Exception {
        long productId = 1L;

        
        mockMvc.perform(delete("/admin/deleteProduct")
               .param("id", String.valueOf(productId)))
               .andExpect(status().isOk())  
               .andExpect(content().string("Product deleted successfully")); 

        
        verify(productService, times(1)).deleteProduct(productId);
    }
    
    @Test
    public void testGetProductById() throws Exception {
        long productId = 1L;

        
        ProductEntity product = ProductEntity.builder()
                                             .id(productId)
                                             .productCode("P001")
                                             .name("Product1")
                                             .category("Category1")
                                             .price(100.0)
                                             .build();

        
        when(productService.getProductById(productId)).thenReturn(product);

        
        mockMvc.perform(get("/getProductById")
               .param("id", String.valueOf(productId)))
               .andExpect(status().isOk())  
               .andExpect(jsonPath("$.id").value(productId))
               .andExpect(jsonPath("$.productCode").value("P001"))
               .andExpect(jsonPath("$.name").value("Product1"))
               .andExpect(jsonPath("$.category").value("Category1"))
               .andExpect(jsonPath("$.price").value(100.0));

        
        verify(productService).getProductById(productId);
    }
}
