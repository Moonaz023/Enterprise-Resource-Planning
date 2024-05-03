package com.erp.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.erp.dto.CheckoutDataDTO;
import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.entity.DistributorEntity;
import com.erp.entity.OrderEntity;
import com.erp.entity.ProductEntity;
import com.erp.repository.DistributorRepository;
import com.erp.repository.OrderRepository;
import com.erp.repository.ProductRepository;
import com.erp.repository.StockRepository;

@Service
public class OrderServiceImp implements OrderService {
	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	private ProductRepository productRepository;
	@Autowired
	private StockRepository stockRepository;
	@Autowired
	private DistributorRepository distributorRepository;

	@Override
	public String addOrder(OrderEntity order) {
		int i = 0;
		long[] products = order.getProduct();
		int[] quantity = order.getProductQuantity();
		StringBuilder orderDetailsBuilder = new StringBuilder();
		for (long productId : products) {
			String productName = productRepository.findProductNameById(productId);
			if (productName != null) {
				orderDetailsBuilder.append(productName).append(":" + quantity[i++]).append(", ");
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
	public CheckoutValidityResultDTO CheckOutValidityTest(long order_id) {
		CheckoutValidityResultDTO checkoutValidityResult = new CheckoutValidityResultDTO();
		Optional<OrderEntity> optionalOrder = orderRepository.findById(order_id);
		double total = 0;
		double price;
		Optional<ProductEntity> optionalOrderedProduct;
		if (optionalOrder.isPresent()) {
			OrderEntity order = optionalOrder.get();
			long[] products = order.getProduct();
			int[] quantities = order.getProductQuantity();
			int i = 0;
			List<CheckoutDataDTO> Checkoutdetails = new ArrayList<>();
			;
			for (long productId : products) {
				int stock = stockRepository.findProductQuantityById(productId);
				if (stock - quantities[i] < 0) {
					checkoutValidityResult.setSuccess(false);
					checkoutValidityResult.setTotalPrice(0);
					checkoutValidityResult.setDetails(null);
					return checkoutValidityResult;
				}

				optionalOrderedProduct = productRepository.findById(productId);
				ProductEntity orderedProduct = optionalOrderedProduct.get();
				price = orderedProduct.getPrice();
				total = total + (quantities[i] * price);
				CheckoutDataDTO checkoutData = new CheckoutDataDTO();
				checkoutData.setProductId(productId);
				checkoutData.setPrice(quantities[i] * price);
				checkoutData.setQuantity(quantities[i]);
				checkoutData.setProductName(orderedProduct.getName());
				Checkoutdetails.add(checkoutData);
				i++;
			}
			checkoutValidityResult.setSuccess(true);
			checkoutValidityResult.setTotalPrice(total);
			checkoutValidityResult.setDetails(Checkoutdetails);
			return checkoutValidityResult;
		} else {

			checkoutValidityResult.setSuccess(false);
			checkoutValidityResult.setTotalPrice(0);
			checkoutValidityResult.setDetails(null);
			return checkoutValidityResult;
		}
	}

	@Override
	public String checkoutNow(CheckoutPaymentDTO checkoutPayment) {
		CheckoutValidityResultDTO validityDTO=CheckOutValidityTest(checkoutPayment.getOrderId());
		boolean validity=validityDTO.isSuccess();
		if(validity==false)
		{
		return "not enough item";
		}
		List<CheckoutDataDTO> details=validityDTO.getDetails();
		for (CheckoutDataDTO order : details) {
			int stock = stockRepository.findProductQuantityById(order.getProductId());
			stock=stock-order.getQuantity();
			Optional<ProductEntity> Optional_product=productRepository.findById(order.getProductId());
			ProductEntity product=Optional_product.get();
			
			stockRepository.updateProductQuantityById(product, stock);
		}
		DistributorEntity distributor= orderRepository.findDistributorByOrderId(checkoutPayment.getOrderId());
		distributor.setTotal_order(distributor.getTotal_order()+1);
		distributorRepository.save(distributor);
		
		orderRepository.deleteById(checkoutPayment.getOrderId());
		return"Checkout successful";
	}

}