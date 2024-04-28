package com.erp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.entity.OrderEntity;
import com.erp.repository.OrderRepository;
import com.erp.repository.ProductRepository;
import com.erp.repository.StockRepository;

@Service
public class OrderServiceImp implements OrderService{
	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StockRepository stockRepository;
	@Override
	public String addOrder(OrderEntity order) {
		int i=0;
	    long[] products = order.getProduct();
	    int[] quantity =order.getProductQuantity();
	    StringBuilder orderDetailsBuilder = new StringBuilder();
	    for (long productId : products) {
	        String productName = productRepository.findProductNameById(productId);
	        if (productName != null) {
	            orderDetailsBuilder.append(productName).append(":"+quantity[i++]).append(", ");
	        }
	    }
	    String orderDetails = orderDetailsBuilder.toString();
	    if (orderDetails.endsWith(", ")) {
	        orderDetails = orderDetails.substring(0, orderDetails.length() - 2); 
	    }
	    order.setOrderDetails(orderDetails);
	    System.out.println("Order Details: " + orderDetails);
	    
	    orderRepository.save(order);
	    return "Order received successfully";
		
	}
	@Override
	public List<OrderEntity> getAllOrder() {
		
		return orderRepository.findAll();	
	}
	@Override
	public Boolean CheckOutValidityTest(long order_id) {
	    Optional<OrderEntity> optionalOrder = orderRepository.findById(order_id);
	    if (optionalOrder.isPresent()) {
	        OrderEntity order = optionalOrder.get();
	        long[] products = order.getProduct();
	        int[] quantities = order.getProductQuantity();
	        int i = 0;
	        for (long productId : products) {
	            int stock = stockRepository.findProductQuantityById(productId);
	            if (stock - quantities[i] < 0) {
	                return false;
	            }
	            System.out.println("Stock Details of Product ID " + productId + ": " + stock);
	            i++;
	        }
	        return true;
	    } else {
	        
	        return false;
	    }
	}
}
