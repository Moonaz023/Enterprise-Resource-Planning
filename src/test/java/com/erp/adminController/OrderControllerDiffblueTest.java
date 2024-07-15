package com.erp.adminController;

import static org.mockito.Mockito.anyLong;
import static org.mockito.Mockito.when;

import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.entity.OrderEntity;
import com.erp.repository.OrderRepository;
import com.erp.service.OrderService;
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

@ContextConfiguration(classes = {OrderController.class})
@ExtendWith(SpringExtension.class)
@DisabledInAotMode
class OrderControllerDiffblueTest {
    @Autowired
    private OrderController orderController;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private OrderService orderService;

    /**
     * Method under test: {@link OrderController#addOrder(OrderEntity)}
     */
    @Test
    void testAddOrder() throws Exception {
        // Arrange
        when(orderService.addOrder(Mockito.<OrderEntity>any())).thenReturn("Add Order");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/addOrder");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Add Order"));
    }

    /**
     * Method under test: {@link OrderController#CheckOutValidityTest(long)}
     */
    @Test
    void testCheckOutValidityTest() throws Exception {
        // Arrange
        CheckoutValidityResultDTO.CheckoutValidityResultDTOBuilder builderResult = CheckoutValidityResultDTO.builder();
        CheckoutValidityResultDTO buildResult = builderResult.details(new ArrayList<>())
                .success(true)
                .totalPrice(10.0d)
                .build();
        when(orderService.CheckOutValidityTest(anyLong())).thenReturn(buildResult);
        MockHttpServletRequestBuilder getResult = MockMvcRequestBuilders.get("/checkOutValidity");
        MockHttpServletRequestBuilder requestBuilder = getResult.param("order_id", String.valueOf(1L));

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("{\"success\":true,\"totalPrice\":10.0,\"details\":[]}"));
    }

    /**
     * Method under test: {@link OrderController#checkoutNow(CheckoutPaymentDTO)}
     */
    @Test
    void testCheckoutNow() throws Exception {
        // Arrange
        when(orderService.checkoutNow(Mockito.<CheckoutPaymentDTO>any())).thenReturn("Checkout Now");
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.post("/checkoutNow");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("text/plain;charset=ISO-8859-1"))
                .andExpect(MockMvcResultMatchers.content().string("Checkout Now"));
    }

    /**
     * Method under test:
     * {@link OrderController#getSecondProducts(Model, HttpSession)}
     */
    @Test
    void testGetSecondProducts() throws Exception {
        // Arrange
        when(orderService.getAllOrder()).thenReturn(new ArrayList<>());
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/getodd");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType("application/json"))
                .andExpect(MockMvcResultMatchers.content().string("[]"));
    }

    /**
     * Method under test: {@link OrderController#index()}
     */
    @Test
    void testIndex() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Order"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Order"));
    }

    /**
     * Method under test: {@link OrderController#index()}
     */
    @Test
    void testIndex2() throws Exception {
        // Arrange
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/order");
        requestBuilder.contentType("https://example.org/example");

        // Act and Assert
        MockMvcBuilders.standaloneSetup(orderController)
                .build()
                .perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.model().size(0))
                .andExpect(MockMvcResultMatchers.view().name("Order"))
                .andExpect(MockMvcResultMatchers.forwardedUrl("Order"));
    }
}
