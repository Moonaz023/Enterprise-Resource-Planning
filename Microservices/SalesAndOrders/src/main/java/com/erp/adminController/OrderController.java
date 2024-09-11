package com.erp.adminController;


import java.security.Principal;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.CheckoutPaymentDTO;
import com.erp.dto.CheckoutValidityResultDTO;
import com.erp.entity.OrderEntity;
import com.erp.repository.OrderRepository;
import com.erp.service.OrderService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class OrderController {

	@Autowired
	public OrderRepository orderRepository;
	@Autowired
	public OrderService orderService;
	
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	
	@GetMapping("/order")
	public String index() {
		return "Order";
	}
	@PostMapping("/addOrder")
	@ResponseBody
	public String addOrder(@ModelAttribute OrderEntity order, HttpSession session,@RequestHeader("Authorization") String token) {
		Long tenantId = 1L;// (Long) session.getAttribute("tenantId");
		
		return orderService.addOrder(order,tenantId,token);
	    
	}

	@GetMapping("/getodd")
	@ResponseBody
	public List<OrderEntity> getSecondProducts(Model m, HttpSession session) {
		Long tenantId = 1L;//(Long) session.getAttribute("tenantId");
		return orderService.getAllOrder(tenantId);
	    
	}
	@GetMapping("/checkOutValidity")
	@ResponseBody
	public CheckoutValidityResultDTO CheckOutValidityTest(@RequestParam long order_id,@RequestHeader("Authorization") String token) {
	
		return orderService.CheckOutValidityTest(order_id,token);
	    
	}
	
	@PostMapping("/checkoutNow")
	@ResponseBody
	public String checkoutNow(@ModelAttribute CheckoutPaymentDTO checkoutPayment, HttpSession session,@RequestHeader("Authorization") String token) {
		Long tenantId =1L;// (Long) session.getAttribute("tenantId");
		
		return orderService.checkoutNow(checkoutPayment,tenantId,token);
	    
	}

//	@GetMapping("/checkOutValidity")
//	@ResponseBody
//	public Boolean CheckOutValidityTest() {
//	
//		return orderService.CheckOutValidityTest();
//	    
//	}


}
