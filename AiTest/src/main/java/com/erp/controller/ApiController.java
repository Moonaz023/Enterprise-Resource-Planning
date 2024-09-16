package com.erp.controller;

import com.erp.entity.PurchaseIngredientEntity;
import com.erp.dto.IngredientBuyAndUseHistoryDTO;
import com.erp.dto.IngredientUseHistoryDTO;
import com.erp.feignclient.ProductAndServiceClient;
import com.erp.feignclient.RowMetaralServiceClient;
/*
import com.erp.service.ApiService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/generate-content")
    public String generateContent(@RequestParam String prompt) {
        return apiService.generateContent(prompt);
    }
}

*/
import com.erp.service.ApiService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
@Controller
@RequestMapping("/admin")
public class ApiController {

    @Autowired
    private ApiService apiService;
    @Autowired
    private RowMetaralServiceClient rowMetaralServiceClient;
    @Autowired
    private ProductAndServiceClient productAndServiceClient;

    @GetMapping("/generate-content")
    public String generateContent(@RequestParam String prompt, Model model) {
        String resultText = apiService.generateContent(prompt);
        model.addAttribute("resultText", resultText);  // Pass HTML to the template
        return "result";
    }
    @GetMapping("/SmartSupport")
    public String SmartSupport() {
    	return "SmartSupport";
    }
    
    @GetMapping("/response")
    @ResponseBody
    public String generateContent(@RequestHeader("Authorization") String token) {
    	List<PurchaseIngredientEntity> list= rowMetaralServiceClient.getAllPurchasedIngredients();
    	System.out.println(list);
    	List<IngredientUseHistoryDTO> list2=productAndServiceClient.getAllItemUseHistory(token);
    	System.out.println(list2);
    	
    	IngredientBuyAndUseHistoryDTO ingredientBuyAndUseHistoryDTO= new IngredientBuyAndUseHistoryDTO();
    	ingredientBuyAndUseHistoryDTO.setPurchageHistory(list);
    	ingredientBuyAndUseHistoryDTO.setUseHistory(list2);
    	
        /*return apiService.generateContent(list+"u can follow the structue and generate data for a year\r\n"
        		+ "\r\n"
        		+ "then\r\n"
        		+ "analysis these data\r\n"
        		+ "and tell me which month is sutable to buy which ingredientName?\r\n"
        		+ "so that i can be more profitable?dont return the list,just give me analysis,you can follow this formet:ING-01 has high demand in the summer months (June, July, August)\r\n"
        		+ "ING-02 has stable demand throughout the year, but its price is consistently lower in January and February.\r\n"
        		+ "Then, based on this limited information:\r\n"
        		+ "\r\n"
        		+ "You might want to purchase ING-01 in the spring months (April/May) to ensure enough inventory for the summer.\r\n"
        		+ "You might want to stock up on ING-02 in January and February to take advantage of the lower prices, even if demand is not highest then.");*/
        return apiService.generateContent(ingredientBuyAndUseHistoryDTO+"ok analysis the data,the unit is same for buy and sell.now tell me which month is sutable to buy which ingredientName?"
        		+ "so that i can be more profitable?"
        		+ "formet:IngrediantName has high demand in the ------ months ,but its price is consistently lower in------,so you should stock IngrediantName nore in those month");
    }
   
}
