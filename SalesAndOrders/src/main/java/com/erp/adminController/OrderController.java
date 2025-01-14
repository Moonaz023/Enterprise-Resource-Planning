package com.erp.adminController;


import java.security.Principal;
import java.util.List;

import com.erp.service.PDFGenerator;
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
	@Autowired
	private PDFGenerator pdfGenerator;

	/*@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}*/
	
	
	@GetMapping("/order")
	public String index() {
		return "Order";
	}
	@PostMapping("/addOrder")
	@ResponseBody
	public String addOrder(@ModelAttribute OrderEntity order, @RequestHeader("Authorization") String token,@RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		
		return orderService.addOrder(order,tenantId,token);
	    
	}

	@GetMapping("/getodd")
	@ResponseBody
	public List<OrderEntity> getSecondProducts(Model m, @RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");
		return orderService.getAllOrder(tenantId);
	    
	}
	@GetMapping("/checkOutValidity")
	@ResponseBody
	public CheckoutValidityResultDTO CheckOutValidityTest(@RequestParam long order_id,@RequestHeader("Authorization") String token) {
	
		return orderService.CheckOutValidityTest(order_id,token);
	    
	}
	
	/*@PostMapping("/checkoutNow")
	@ResponseBody
	public String checkoutNow(@ModelAttribute CheckoutPaymentDTO checkoutPayment, @RequestHeader("Authorization") String token,@RequestHeader("tenantId") String tenant) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		
		return orderService.checkoutNow(checkoutPayment,tenantId,token);
	    
	}*/

	@PostMapping("/checkoutNow")
	@ResponseBody
	public byte[] checkoutNow(@ModelAttribute CheckoutPaymentDTO checkoutPayment, @RequestHeader("Authorization") String token,@RequestHeader("tenantId") String tenant,@RequestHeader("c_name") String c_name) {
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");

		return pdfGenerator.generatePDF(checkoutPayment,tenantId,token,c_name);

	}
//	@GetMapping("/checkOutValidity")
//	@ResponseBody
//	public Boolean CheckOutValidityTest() {
//	
//		return orderService.CheckOutValidityTest();
//	    
//	}

@GetMapping("/creat_pdf")
@ResponseBody
public byte[] CreatPDF(@RequestParam long order_id,@RequestHeader("Authorization") String token,@RequestHeader("c_name") String c_name) {
	return pdfGenerator.generatePDF(order_id,token,false,c_name);

}

}
