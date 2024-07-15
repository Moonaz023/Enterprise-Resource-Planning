package com.erp.service;


import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;


import com.erp.entity.ProductEntity;
import com.erp.repository.ProductRepository;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

    @Mock
    private ProductRepository productRepository;

    @InjectMocks
    private ProductServiceImp productService;

    private ProductEntity product ;
    private List<ProductEntity> productList;
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        product  = ProductEntity.builder()
        		.id(1)
                .productCode("P001")
                .name("Product1")
                .category("Category1")
                .price(100.0)
                .build();
        productList = Arrays.asList(
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
    }
    
    @Test
    public void testGetAllProducts() {


        when(productRepository.findAll()).thenReturn(productList);
        

        List<ProductEntity> result = productService.getAllproduct();

        assertEquals(productList, result);
    }
    @Test
    public void testGetAllProducts2() {


        
        when(productRepository.findAll()).thenReturn(
        	new ArrayList<>(Collections.singleton(product))	
        		);

        List<ProductEntity> result = productService.getAllproduct();

        assertEquals(product.getName(), result.get(0).getName());
        assertEquals(product, result.get(0));
    }

	@Test
    public void testGetAllProductsEmpty() {
        when(productRepository.findAll()).thenReturn(Arrays.asList());

        List<ProductEntity> result = productService.getAllproduct();

        assertEquals(0, result.size());
    }

	@Test
    public void testGetAllProductsException() {
        when(productRepository.findAll()).thenThrow(new RuntimeException("Exception occurred"));

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            productService.getAllproduct();
        });

        assertEquals("Exception occurred", exception.getMessage());
    }


    @Test
    public void testgetProductById() {
		when(productRepository.findById(1L)).thenReturn(Optional.of(product));
		ProductEntity result=productService.getProductById(1L);
		assertEquals(product, result);
		
	}
	@Test
    public void testgetProductByIdNotExist() {
		when(productRepository.findById(2L)).thenReturn(Optional.empty());
		ProductEntity result=productService.getProductById(2L);
		assertNull(result);
		
	}
	@Test
    public void testupdateProduct() {
		
        productService.updateProduct(product);
        //verify(productRepository,times(1)).save(any(ProductEntity.class));
        verify(productRepository,times(1)).save(product);
    }
	
	@Test
    public void testDeleteProduct()
    {
		productService.deleteProduct(1L);
		verify(productRepository,times(1)).deleteById(1L);
    }
	
}
