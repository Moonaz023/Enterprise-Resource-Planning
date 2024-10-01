package com.erp.adminController;

import java.security.Principal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import com.erp.entity.IngredientEntity;
import com.erp.entity.ProductEntity;
import com.erp.feignclient.RowMetaralServiceClient;
import com.erp.service.ProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class ProductController {
	@Autowired
	private ProductService productService;

	@Autowired
	private RowMetaralServiceClient rowMetaralServiceClient;
	
	/*@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}*/
	
	@GetMapping("/product")
	public String index() {
		//System.out.println(authorizationHeader);
		return "Product";
	}
	
	@GetMapping("/getTheIn")//just testinggg nothisg else...................
	@ResponseBody
	public IngredientEntity trygetin(@RequestHeader("Authorization") String token, @RequestHeader("tenantId") String tenant )
	{IngredientEntity ingredientEntity =rowMetaralServiceClient.getIngredientsById( Long.parseLong(tenant),token);
	return ingredientEntity;}

	@GetMapping("/getAllProducts")
	@ResponseBody
	public List<ProductEntity> getAllproduct(Model m,@RequestHeader("Authorization") String token,  @RequestHeader("tenantId") String tenant ) {
		//System.out.println("authorizationHeader_______>>>"+token);
		
		Long tenantId = Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");

		List<ProductEntity> listOfproduct = productService.getAllproduct(tenantId);

		return listOfproduct;
	}

	@PostMapping("/saveProduct")
	@ResponseBody
	public void saveProduct(@ModelAttribute ProductEntity product,  @RequestHeader("tenantId") String tenant ) {
		Long tenantId =  Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");

		System.out.println(product);
		productService.saveProduct(product, tenantId);
		//return "redirect:/";
	}

	@PostMapping("/updateProduct")
	@ResponseBody
	public String updateProduct(@ModelAttribute ProductEntity updatedProduct,  @RequestHeader("tenantId") String tenant) {
		Long tenantId =  Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");

		productService.updateProduct(updatedProduct, tenantId);

		return "Product record updated successfully";
	}

	@DeleteMapping("/deleteProduct")
	@ResponseBody
	public String deleteProduct(@RequestParam("id") long id) {
		productService.deleteProduct(id);
		return "Product deleted successfully";
	}

	@GetMapping("/getProductById")
	@ResponseBody
	public ProductEntity getProductById(@RequestParam("id") long id) {
		return productService.getProductById(id);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<String> handleException(Exception ex, WebRequest request) {
		return new ResponseEntity<>("Internal Server Error: " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
