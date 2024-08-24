package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.SalesOverviewDTO;
import com.erp.service.SalesOverviewService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class SalesOverviewController {
	@Autowired
	private SalesOverviewService salesOverviewService;

	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}

	@GetMapping("/SalesOverview")
	public String index() {
		return "SalesOverview";

	}

	/*
	 * @GetMapping("/getSalesOverview") public
	 * ResponseEntity<List<SalesOverviewDTO>> getSalesOverview() {
	 * List<SalesOverviewDTO> salesOverview=salesOverviewService.getSalesOverview();
	 * 
	 * if (salesOverview.isEmpty()) { return ResponseEntity.notFound().build(); }
	 * else { return ResponseEntity.ok(salesOverview); }
	 * 
	 * }
	 */
	@GetMapping("/getSalesOverview")
	@ResponseBody
	public List<SalesOverviewDTO> getSalesOverview(HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		List<SalesOverviewDTO> salesOverview = salesOverviewService.getSalesOverview(tenantId);
		return salesOverview;
	}
}
