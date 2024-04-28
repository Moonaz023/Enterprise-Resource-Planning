package com.erp.service;

import java.util.List;

import com.erp.entity.OrderEntity;

public interface OrderService {

	String addOrder(OrderEntity order);

	List<OrderEntity> getAllOrder();

	Boolean CheckOutValidityTest(long order_id);
	

}
