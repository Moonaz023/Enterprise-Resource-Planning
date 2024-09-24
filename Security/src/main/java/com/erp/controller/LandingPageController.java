package com.erp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LandingPageController {
	@GetMapping("/")
    public String index()
    {
    	return "index";
    }
	
	@GetMapping("/signup")
    public String sinup()
    {
    	return "register";
    }

}
