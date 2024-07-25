package com.erp.adminController;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.erp.entity.UnitEntity;
import com.erp.repository.UnitRepository;

import jakarta.servlet.http.HttpSession;

@Controller
public class UnitController {
	@Autowired
	private UnitRepository unitRepository;
	@GetMapping("/getAllUnits")
	@ResponseBody
	public List<UnitEntity> getAllDistributors(Model m, HttpSession session) {
		
	    List<UnitEntity> listOfUnit = unitRepository.findAll();

	    return listOfUnit;
	}

}
