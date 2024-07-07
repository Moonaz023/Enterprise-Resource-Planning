package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.SalesOverviewDTO;
import com.erp.service.SalesOverviewService;

@Controller
public class SalesOverviewController {
@Autowired
private SalesOverviewService salesOverviewService;
@GetMapping("/SalesOverview")
public String index() {
	return "SalesOverview";
	
}
/*
@GetMapping("/getSalesOverview")
public ResponseEntity<List<SalesOverviewDTO>> getSalesOverview() {
	List<SalesOverviewDTO> salesOverview=salesOverviewService.getSalesOverview();
	
	if (salesOverview.isEmpty()) {
        return ResponseEntity.notFound().build();
    } else {
        return ResponseEntity.ok(salesOverview);
    }
	
}
*/
@GetMapping("/getSalesOverview")
@ResponseBody
public List<SalesOverviewDTO> getSalesOverview() {
	List<SalesOverviewDTO> salesOverview=salesOverviewService.getSalesOverview();
	return salesOverview;
}
}
