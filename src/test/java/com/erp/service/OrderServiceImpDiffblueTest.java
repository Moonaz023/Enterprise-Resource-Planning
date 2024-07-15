package com.erp.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.anyInt;
import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.erp.dto.CheckoutDataDTO;
import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.OrderEntity;
import com.erp.entity.ProductEntity;
import com.erp.entity.SalesReportEntity;
import com.erp.repository.DistributorRepository;
import com.erp.repository.OrderRepository;
import com.erp.repository.ProductBatchesStockRepository;
import com.erp.repository.ProductRepository;
import com.erp.repository.SalesReportRepository;
import com.erp.repository.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.aot.DisabledInAotMode;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {OrderServiceImp.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OrderServiceImpDiffblueTest {
    @MockBean
    private DistributorRepository distributorRepository;

    @MockBean
    private OrderRepository orderRepository;

    @Autowired
    private OrderServiceImp orderServiceImp;

    @MockBean
    private ProductBatchesStockRepository productBatchesStockRepository;

    @MockBean
    private ProductRepository productRepository;

    @MockBean
    private SalesReportRepository salesReportRepository;

    @MockBean
    private StockRepository stockRepository;

    /**
     * Method under test: {@link OrderServiceImp#addOrder(OrderEntity)}
     */
    @Test
    void testAddOrder() {
        // Arrange
        DistributorEntity distributor_id = new DistributorEntity();
        distributor_id.setAddress("42 Main St");
        distributor_id.setEmail("jane.doe@example.org");
        distributor_id.setId(1L);
        distributor_id.setName("Name");
        distributor_id.setPhone("6625550144");
        distributor_id.setTotal_order(1L);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate("2020-03-01");
        orderEntity.setDistributor_id(distributor_id);
        orderEntity.setId(1L);
        orderEntity.setOrderDetails("Order Details");
        orderEntity.setProduct(new long[]{1L, -1L, 1L, -1L});
        orderEntity.setProductQuantity(new int[]{1, -1, 1, -1});
        when(orderRepository.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);
        when(productRepository.findProductNameById(Mockito.<Long>any())).thenReturn("42");

        DistributorEntity distributor_id2 = new DistributorEntity();
        distributor_id2.setAddress("42 Main St");
        distributor_id2.setEmail("jane.doe@example.org");
        distributor_id2.setId(1L);
        distributor_id2.setName("Name");
        distributor_id2.setPhone("6625550144");
        distributor_id2.setTotal_order(1L);

        OrderEntity order = new OrderEntity();
        order.setDate("2020-03-01");
        order.setDistributor_id(distributor_id2);
        order.setId(1L);
        order.setOrderDetails("Order Details");
        order.setProduct(new long[]{1L, -1L, 1L, -1L});
        order.setProductQuantity(new int[]{1, -1, 1, -1});

        // Act
        String actualAddOrderResult = orderServiceImp.addOrder(order);

        // Assert
        verify(productRepository, atLeast(1)).findProductNameById(Mockito.<Long>any());
        verify(orderRepository).save(isA(OrderEntity.class));
        assertEquals("42:1, 42:-1, 42:1, 42:-1", order.getOrderDetails());
        assertEquals("Order received successfully", actualAddOrderResult);
    }

    /**
     * Method under test: {@link OrderServiceImp#addOrder(OrderEntity)}
     */
    @Test
    void testAddOrder2() {
        // Arrange
        DistributorEntity distributor_id = new DistributorEntity();
        distributor_id.setAddress("42 Main St");
        distributor_id.setEmail("jane.doe@example.org");
        distributor_id.setId(1L);
        distributor_id.setName("Name");
        distributor_id.setPhone("6625550144");
        distributor_id.setTotal_order(1L);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate("2020-03-01");
        orderEntity.setDistributor_id(distributor_id);
        orderEntity.setId(1L);
        orderEntity.setOrderDetails("Order Details");
        orderEntity.setProduct(new long[]{1L, -1L, 1L, -1L});
        orderEntity.setProductQuantity(new int[]{1, -1, 1, -1});
        when(orderRepository.save(Mockito.<OrderEntity>any())).thenReturn(orderEntity);
        when(productRepository.findProductNameById(Mockito.<Long>any())).thenReturn(null);

        DistributorEntity distributor_id2 = new DistributorEntity();
        distributor_id2.setAddress("42 Main St");
        distributor_id2.setEmail("jane.doe@example.org");
        distributor_id2.setId(1L);
        distributor_id2.setName("Name");
        distributor_id2.setPhone("6625550144");
        distributor_id2.setTotal_order(1L);

        OrderEntity order = new OrderEntity();
        order.setDate("2020-03-01");
        order.setDistributor_id(distributor_id2);
        order.setId(1L);
        order.setOrderDetails("Order Details");
        order.setProduct(new long[]{1L, -1L, 1L, -1L});
        order.setProductQuantity(new int[]{1, -1, 1, -1});

        // Act
        String actualAddOrderResult = orderServiceImp.addOrder(order);

        // Assert
        verify(productRepository, atLeast(1)).findProductNameById(Mockito.<Long>any());
        verify(orderRepository).save(isA(OrderEntity.class));
        assertEquals("", order.getOrderDetails());
        assertEquals("Order received successfully", actualAddOrderResult);
    }

    /**
     * Method under test: {@link OrderServiceImp#getAllOrder()}
     */
    @Test
    void testGetAllOrder() {
        // Arrange
        ArrayList<OrderEntity> orderEntityList = new ArrayList<>();
        when(orderRepository.findAll()).thenReturn(orderEntityList);

        // Act
        List<OrderEntity> actualAllOrder = orderServiceImp.getAllOrder();

        // Assert
        verify(orderRepository).findAll();
        assertTrue(actualAllOrder.isEmpty());
        assertSame(orderEntityList, actualAllOrder);
    }

    /**
     * Method under test: {@link OrderServiceImp#CheckOutValidityTest(long)}
     */
    @Test
    void testCheckOutValidityTest() {
        // Arrange
        DistributorEntity distributor_id = new DistributorEntity();
        distributor_id.setAddress("42 Main St");
        distributor_id.setEmail("jane.doe@example.org");
        distributor_id.setId(1L);
        distributor_id.setName("Name");
        distributor_id.setPhone("6625550144");
        distributor_id.setTotal_order(1L);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate("2020-03-01");
        orderEntity.setDistributor_id(distributor_id);
        orderEntity.setId(1L);
        orderEntity.setOrderDetails("Order Details");
        orderEntity.setProduct(new long[]{1L, -1L, 1L, -1L});
        orderEntity.setProductQuantity(new int[]{1, -1, 1, -1});
        Optional<OrderEntity> ofResult = Optional.of(orderEntity);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        Optional<ProductEntity> ofResult2 = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        when(stockRepository.findProductQuantityById(Mockito.<Long>any())).thenReturn(1);

        // Act
        CheckoutValidityResultDTO actualCheckOutValidityTestResult = orderServiceImp.CheckOutValidityTest(1L);

        // Assert
        verify(stockRepository, atLeast(1)).findProductQuantityById(Mockito.<Long>any());
        verify(productRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(orderRepository).findById(eq(1L));
        List<CheckoutDataDTO> details = actualCheckOutValidityTestResult.getDetails();
        assertEquals(4, details.size());
        CheckoutDataDTO getResult = details.get(0);
        assertEquals("Name", getResult.getProductName());
        CheckoutDataDTO getResult2 = details.get(1);
        assertEquals("Name", getResult2.getProductName());
        CheckoutDataDTO getResult3 = details.get(2);
        assertEquals("Name", getResult3.getProductName());
        CheckoutDataDTO getResult4 = details.get(3);
        assertEquals("Name", getResult4.getProductName());
        assertEquals(-1, getResult2.getQuantity());
        assertEquals(-1, getResult4.getQuantity());
        assertEquals(-10.0d, getResult2.getPrice());
        assertEquals(-10.0d, getResult4.getPrice());
        assertEquals(-1L, getResult2.getProductId());
        assertEquals(-1L, getResult4.getProductId());
        assertEquals(0.0d, actualCheckOutValidityTestResult.getTotalPrice());
        assertEquals(1, getResult.getQuantity());
        assertEquals(1, getResult3.getQuantity());
        assertEquals(10.0d, getResult.getPrice());
        assertEquals(10.0d, getResult3.getPrice());
        assertEquals(1L, getResult.getProductId());
        assertEquals(1L, getResult3.getProductId());
        assertTrue(actualCheckOutValidityTestResult.isSuccess());
    }

    /**
     * Method under test: {@link OrderServiceImp#CheckOutValidityTest(long)}
     */
    @Test
    void testCheckOutValidityTest2() {
        // Arrange
        DistributorEntity distributor_id = new DistributorEntity();
        distributor_id.setAddress("42 Main St");
        distributor_id.setEmail("jane.doe@example.org");
        distributor_id.setId(1L);
        distributor_id.setName("Name");
        distributor_id.setPhone("6625550144");
        distributor_id.setTotal_order(1L);
        OrderEntity orderEntity = mock(OrderEntity.class);
        when(orderEntity.getProductQuantity()).thenReturn(new int[]{Integer.MIN_VALUE, -1, 1, -1});
        when(orderEntity.getProduct()).thenReturn(new long[]{1L, -1L, 1L, -1L});
        doNothing().when(orderEntity).setDate(Mockito.<String>any());
        doNothing().when(orderEntity).setDistributor_id(Mockito.<DistributorEntity>any());
        doNothing().when(orderEntity).setId(anyLong());
        doNothing().when(orderEntity).setOrderDetails(Mockito.<String>any());
        doNothing().when(orderEntity).setProduct(Mockito.<long[]>any());
        doNothing().when(orderEntity).setProductQuantity(Mockito.<int[]>any());
        orderEntity.setDate("2020-03-01");
        orderEntity.setDistributor_id(distributor_id);
        orderEntity.setId(1L);
        orderEntity.setOrderDetails("Order Details");
        orderEntity.setProduct(new long[]{1L, -1L, 1L, -1L});
        orderEntity.setProductQuantity(new int[]{1, -1, 1, -1});
        Optional<OrderEntity> ofResult = Optional.of(orderEntity);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        Optional<ProductEntity> ofResult2 = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        when(stockRepository.findProductQuantityById(Mockito.<Long>any())).thenReturn(1);

        // Act
        CheckoutValidityResultDTO actualCheckOutValidityTestResult = orderServiceImp.CheckOutValidityTest(1L);

        // Assert
        verify(orderEntity).getProduct();
        verify(orderEntity).getProductQuantity();
        verify(orderEntity).setDate(eq("2020-03-01"));
        verify(orderEntity).setDistributor_id(isA(DistributorEntity.class));
        verify(orderEntity).setId(eq(1L));
        verify(orderEntity).setOrderDetails(eq("Order Details"));
        verify(orderEntity).setProduct(isA(long[].class));
        verify(orderEntity).setProductQuantity(isA(int[].class));
        verify(stockRepository).findProductQuantityById(eq(1L));
        verify(orderRepository).findById(eq(1L));
        assertNull(actualCheckOutValidityTestResult.getDetails());
        assertEquals(0.0d, actualCheckOutValidityTestResult.getTotalPrice());
        assertFalse(actualCheckOutValidityTestResult.isSuccess());
    }

    /**
     * Method under test: {@link OrderServiceImp#CheckOutValidityTest(long)}
     */
    @Test
    void testCheckOutValidityTest3() {
        // Arrange
        Optional<OrderEntity> emptyResult = Optional.empty();
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(emptyResult);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        Optional<ProductEntity> ofResult = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        // Act
        CheckoutValidityResultDTO actualCheckOutValidityTestResult = orderServiceImp.CheckOutValidityTest(1L);

        // Assert
        verify(orderRepository).findById(eq(1L));
        assertNull(actualCheckOutValidityTestResult.getDetails());
        assertEquals(0.0d, actualCheckOutValidityTestResult.getTotalPrice());
        assertFalse(actualCheckOutValidityTestResult.isSuccess());
    }

    /**
     * Method under test: {@link OrderServiceImp#checkoutNow(CheckoutPaymentDTO)}
     */
    @Test
    void testCheckoutNow() {
        // Arrange
        DistributorEntity distributorEntity = new DistributorEntity();
        distributorEntity.setAddress("42 Main St");
        distributorEntity.setEmail("jane.doe@example.org");
        distributorEntity.setId(1L);
        distributorEntity.setName("Name");
        distributorEntity.setPhone("6625550144");
        distributorEntity.setTotal_order(1L);
        when(distributorRepository.save(Mockito.<DistributorEntity>any())).thenReturn(distributorEntity);

        DistributorEntity distributor_id = new DistributorEntity();
        distributor_id.setAddress("42 Main St");
        distributor_id.setEmail("jane.doe@example.org");
        distributor_id.setId(1L);
        distributor_id.setName("Name");
        distributor_id.setPhone("6625550144");
        distributor_id.setTotal_order(1L);

        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setDate("2020-03-01");
        orderEntity.setDistributor_id(distributor_id);
        orderEntity.setId(1L);
        orderEntity.setOrderDetails("Order Details");
        orderEntity.setProduct(new long[]{1L, -1L, 1L, -1L});
        orderEntity.setProductQuantity(new int[]{1, -1, 1, -1});
        Optional<OrderEntity> ofResult = Optional.of(orderEntity);

        DistributorEntity distributorEntity2 = new DistributorEntity();
        distributorEntity2.setAddress("42 Main St");
        distributorEntity2.setEmail("jane.doe@example.org");
        distributorEntity2.setId(1L);
        distributorEntity2.setName("Name");
        distributorEntity2.setPhone("6625550144");
        distributorEntity2.setTotal_order(1L);
        doNothing().when(orderRepository).deleteById(Mockito.<Long>any());
        when(orderRepository.findDistributorByOrderId(anyLong())).thenReturn(distributorEntity2);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);
        when(productBatchesStockRepository.findByProduct(Mockito.<ProductEntity>any())).thenReturn(new ArrayList<>());

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        Optional<ProductEntity> ofResult2 = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);

        DistributorEntity distributor = new DistributorEntity();
        distributor.setAddress("42 Main St");
        distributor.setEmail("jane.doe@example.org");
        distributor.setId(1L);
        distributor.setName("Name");
        distributor.setPhone("6625550144");
        distributor.setTotal_order(1L);

        SalesReportEntity salesReportEntity = new SalesReportEntity();
        salesReportEntity.setDate("2020-03-01");
        salesReportEntity.setDetails("Details");
        salesReportEntity.setDistributor(distributor);
        salesReportEntity.setDue(10.0d);
        salesReportEntity.setId(1L);
        salesReportEntity.setItemAndQuantity(new ArrayList<>());
        salesReportEntity.setReceptAmount(10.0d);
        when(salesReportRepository.save(Mockito.<SalesReportEntity>any())).thenReturn(salesReportEntity);
        doNothing().when(stockRepository).updateProductQuantityById(Mockito.<ProductEntity>any(), anyInt());
        when(stockRepository.findProductQuantityById(Mockito.<Long>any())).thenReturn(1);

        // Act
        String actualCheckoutNowResult = orderServiceImp.checkoutNow(new CheckoutPaymentDTO(1L, 10.0d));

        // Assert
        verify(orderRepository).findDistributorByOrderId(eq(1L));
        verify(productBatchesStockRepository, atLeast(1)).findByProduct(isA(ProductEntity.class));
        verify(stockRepository, atLeast(1)).findProductQuantityById(Mockito.<Long>any());
        verify(stockRepository, atLeast(1)).updateProductQuantityById(isA(ProductEntity.class), anyInt());
        verify(orderRepository).deleteById(eq(1L));
        verify(productRepository, atLeast(1)).findById(Mockito.<Long>any());
        verify(orderRepository).findById(eq(1L));
        verify(distributorRepository).save(isA(DistributorEntity.class));
        verify(salesReportRepository).save(isA(SalesReportEntity.class));
        assertEquals("Checkout successful", actualCheckoutNowResult);
    }

    /**
     * Method under test: {@link OrderServiceImp#checkoutNow(CheckoutPaymentDTO)}
     */
    @Test
    void testCheckoutNow2() {
        // Arrange
        DistributorEntity distributor_id = new DistributorEntity();
        distributor_id.setAddress("42 Main St");
        distributor_id.setEmail("jane.doe@example.org");
        distributor_id.setId(1L);
        distributor_id.setName("Name");
        distributor_id.setPhone("6625550144");
        distributor_id.setTotal_order(1L);
        OrderEntity orderEntity = mock(OrderEntity.class);
        when(orderEntity.getProductQuantity()).thenReturn(new int[]{Integer.MIN_VALUE, -1, 1, -1});
        when(orderEntity.getProduct()).thenReturn(new long[]{1L, -1L, 1L, -1L});
        doNothing().when(orderEntity).setDate(Mockito.<String>any());
        doNothing().when(orderEntity).setDistributor_id(Mockito.<DistributorEntity>any());
        doNothing().when(orderEntity).setId(anyLong());
        doNothing().when(orderEntity).setOrderDetails(Mockito.<String>any());
        doNothing().when(orderEntity).setProduct(Mockito.<long[]>any());
        doNothing().when(orderEntity).setProductQuantity(Mockito.<int[]>any());
        orderEntity.setDate("2020-03-01");
        orderEntity.setDistributor_id(distributor_id);
        orderEntity.setId(1L);
        orderEntity.setOrderDetails("Order Details");
        orderEntity.setProduct(new long[]{1L, -1L, 1L, -1L});
        orderEntity.setProductQuantity(new int[]{1, -1, 1, -1});
        Optional<OrderEntity> ofResult = Optional.of(orderEntity);
        when(orderRepository.findById(Mockito.<Long>any())).thenReturn(ofResult);

        ProductEntity productEntity = new ProductEntity();
        productEntity.setCategory("Category");
        productEntity.setId(1L);
        productEntity.setName("Name");
        productEntity.setPrice(10.0d);
        productEntity.setProductCode("Product Code");
        productEntity.setProductions(new ArrayList<>());
        Optional<ProductEntity> ofResult2 = Optional.of(productEntity);
        when(productRepository.findById(Mockito.<Long>any())).thenReturn(ofResult2);
        when(stockRepository.findProductQuantityById(Mockito.<Long>any())).thenReturn(1);

        // Act
        String actualCheckoutNowResult = orderServiceImp.checkoutNow(new CheckoutPaymentDTO(1L, 10.0d));

        // Assert
        verify(orderEntity).getProduct();
        verify(orderEntity).getProductQuantity();
        verify(orderEntity).setDate(eq("2020-03-01"));
        verify(orderEntity).setDistributor_id(isA(DistributorEntity.class));
        verify(orderEntity).setId(eq(1L));
        verify(orderEntity).setOrderDetails(eq("Order Details"));
        verify(orderEntity).setProduct(isA(long[].class));
        verify(orderEntity).setProductQuantity(isA(int[].class));
        verify(stockRepository).findProductQuantityById(eq(1L));
        verify(orderRepository).findById(eq(1L));
        assertEquals("not enough item", actualCheckoutNowResult);
    }
}
