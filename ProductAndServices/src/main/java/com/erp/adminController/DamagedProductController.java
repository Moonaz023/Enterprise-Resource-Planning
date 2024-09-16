package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.DamagedProductEntity;
import com.erp.service.DamagedProductService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DamagedProductController {
	@Autowired
	private DamagedProductService damagedProductService;
	
//	@ModelAttribute
//	public void commonUser(Principal p, Model user) {
//		if (p != null) {
//			String name = p.getName();
//
//			user.addAttribute("user", name);
//		}
//	}
	@GetMapping("/damagedProduct")
	public String index() {
		return "DamagedProduct";

	}

	@PostMapping("/saveDamagedProduct")
	@ResponseBody
	public String saveDamagedProduct(@ModelAttribute DamagedProductEntity damagedProduct, HttpSession session) {
		Long tenantId = 1L;// (Long) session.getAttribute("tenantId");
		damagedProductService.save(damagedProduct,tenantId);
		return "ok";

	}
	@GetMapping("/getAllDamagedProduct")
	@ResponseBody
	public List<DamagedProductEntity> getAllDamagedProduct(HttpSession session) {
		Long tenantId =  1L;//(Long) session.getAttribute("tenantId");
		return damagedProductService.findAll(tenantId);
	}

}
