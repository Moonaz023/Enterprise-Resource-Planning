package com.erp.adminController;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.DistributorEntity;
import com.erp.entity.OrderDOT;
import com.erp.repository.OrderDOTRepository;
import com.erp.repository.ProductRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class OrderController {

	@Autowired
	public OrderDOTRepository orderDOTRepository;
	@Autowired
	private ProductRepository productRepository;
	@GetMapping("/order")
	public String index() {
		return "Order";
	}
	@PostMapping("/addOrder")
	@ResponseBody
	public String addOrder(@ModelAttribute OrderDOT order) {
		int i=0;
	    long[] products = order.getProduct();
	    int[] quantity =order.getProductQuantity();
	    StringBuilder orderDetailsBuilder = new StringBuilder();
	    for (long productId : products) {
	        String productName = productRepository.findProductNameById(productId);
	        if (productName != null) {
	            orderDetailsBuilder.append(productName).append(":"+quantity[i++]).append(",");
	        }
	    }
	    String orderDetails = orderDetailsBuilder.toString();
	    if (orderDetails.endsWith(", ")) {
	        orderDetails = orderDetails.substring(0, orderDetails.length() - 2); 
	    }
	    order.setOrderDetails(orderDetails);
	    System.out.println("Order Details: " + orderDetails);
	    orderDOTRepository.save(order);
	    return "Order received successfully";
	}

	@GetMapping("/getodd")
	@ResponseBody
	public List<OrderDOT> getSecondProducts(Model m, HttpSession session) {
	    List<OrderDOT> listOfOrders = orderDOTRepository.findAll();
//	    long[] products;
//	    for (OrderDOT order : listOfOrders) {
//	        products = order.getProduct();
//	        //System.out.println(products[1]);
////	        if (products != null && products.length > 1) {
////	            secondProducts.add(products[1]);
////	        } else {
////	            secondProducts.add("No second product found");
////	        }
//	    }
	    
	    return listOfOrders;
	}
//	@GetMapping("/getsecondproducts")
//    @ResponseBody
//    public List<String> getSecondProducts() {
//        List<OrderDOT> listOfOrders = orderDOTRepository.findAll();
//        List<String> secondProducts = new ArrayList<>();
//
//        // Loop through each OrderDOT object and retrieve the second product
//        for (OrderDOT order : listOfOrders) {
//            String[] products = order.getProduct();
//            // Check if the products array has at least two elements
//            if (products != null && products.length >= 2) {
//                // Add the second product to the list
//                secondProducts.add(products[1]);
//            }
//        }
//
//        return secondProducts;
//    }

}
