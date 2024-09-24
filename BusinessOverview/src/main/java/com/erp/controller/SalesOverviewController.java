package com.erp.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
@RequestMapping("/admin")
public class SalesOverviewController {
	

	@GetMapping("/SalesOverview")
	public String index() {
		return "SalesOverview";

	}

	
	
}
