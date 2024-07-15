package com.erp.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import com.erp.entity.ProductEntity;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ProductRepositoryTest {

    @Autowired
    private ProductRepository productRepository;

    private ProductEntity product;

    @BeforeEach
    void setUp() {
        product = new ProductEntity();
        product.setProductCode("p003");
        product.setName("ice cream");
        product.setCategory("chocolate");
        product.setPrice(30.0);

        productRepository.save(product);
    }

    @AfterEach
    void tearDown() {
        productRepository.deleteAll();
    }

    @Test
    void testFindProductNameById() {
        String name = productRepository.findProductNameById(product.getId());
       
        assertEquals(product.getName(), name);
       
    }
    @Test
    void testFindProductNameByIdNotFound() {
       
        String nullName = productRepository.findProductNameById(999L); 

      
        assertNull(nullName);
    }

    @Test
    void testFindProductPriceById() {
        double price = productRepository.findProductPriceById(product.getId());
        
        assertEquals(product.getPrice(), price);
        
    }
}
