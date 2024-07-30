package com.erp.service;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.entity.ProductEntity;
import com.erp.repository.ProductRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@ContextConfiguration(classes = {ProductServiceImp.class})
@RunWith(SpringJUnit4ClassRunner.class)
@DisabledInAotMode
public class ProductServiceImpDiffblueTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImp productServiceImp;

    /**
     * Method under test: {@link ProductServiceImp#saveProduct(ProductEntity)}
     */
    @Test
    public void testSaveProduct() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        productEntity.setUnitPrice(new ArrayList<>());
        when(productRepository.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);

        ProductEntity product = new ProductEntity();
        product.setCategory("Category");
        product.setId(1L);
        product.setName("Name");
        product.setProductCode("Product Code");
        product.setProductions(new ArrayList<>());
        product.setUnitPrice(new ArrayList<>());

        // Act
        productServiceImp.saveProduct(product);

        // Assert that nothing has changed
        verify(productRepository).save(isA(ProductEntity.class));
    }

    /**
     * Method under test: {@link ProductServiceImp#getAllproduct()}
     */
    @Test
    public void testGetAllproduct() {
        // Arrange
        ArrayList<ProductEntity> productEntityList = new ArrayList<>();
        when(productRepository.findAll()).thenReturn(productEntityList);

        // Act
        List<ProductEntity> actualAllproduct = productServiceImp.getAllproduct();

        // Assert
        verify(productRepository).findAll();
        assertTrue(actualAllproduct.isEmpty());
        assertSame(productEntityList, actualAllproduct);
    }

    /**
     * Method under test: {@link ProductServiceImp#getProductById(long)}
     */
    @Test
    public void testGetProductById() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        productEntity.setUnitPrice(new ArrayList<>());
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        ProductEntity actualProductById = productServiceImp.getProductById(1L);

        // Assert
        verify(productRepository).findById(eq(1L));
        assertSame(productEntity, actualProductById);
    }

    /**
     * Method under test: {@link ProductServiceImp#updateProduct(ProductEntity)}
     */
    @Test
    public void testUpdateProduct() {
        // Arrange
        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        productEntity.setUnitPrice(new ArrayList<>());
        when(productRepository.save(Mockito.<ProductEntity>any())).thenReturn(productEntity);

        ProductEntity updatedProduct = new ProductEntity();
        updatedProduct.setCategory("Category");
        updatedProduct.setId(1L);
        updatedProduct.setName("Name");
        updatedProduct.setProductCode("Product Code");
        updatedProduct.setProductions(new ArrayList<>());
        updatedProduct.setUnitPrice(new ArrayList<>());

        // Act
        productServiceImp.updateProduct(updatedProduct);

        // Assert that nothing has changed
        verify(productRepository).save(isA(ProductEntity.class));
    }

    /**
     * Method under test: {@link ProductServiceImp#deleteProduct(long)}
     */
    @Test
    public void testDeleteProduct() {
        // Arrange
        doNothing().when(productRepository).deleteById(Mockito.<Long>any());

        // Act
        productServiceImp.deleteProduct(1L);

        // Assert that nothing has changed
        verify(productRepository).deleteById(eq(1L));
    }
}
