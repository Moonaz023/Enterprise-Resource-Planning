package com.erp.service;

import java.util.List;

import com.erp.entity.OrderEntity;
import com.erp.entity.CheckoutValidityResultDOT;

public interface OrderService {

	String addOrder(OrderEntity order);

	List<OrderEntity> getAllOrder();

	CheckoutValidityResultDOT CheckOutValidityTest(long order_id);
	

}
