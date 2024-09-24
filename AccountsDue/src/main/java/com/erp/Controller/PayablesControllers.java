package com.erp.Controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping("/admin")
public class PayablesControllers {


	
	@GetMapping("/payables")
	public String index() {
		return "Payables";
	}


}
