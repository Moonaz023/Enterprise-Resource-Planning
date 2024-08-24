package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import com.erp.entity.UnitEntity;
import com.erp.repository.UnitRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UnitController {
	@Autowired
	private UnitRepository unitRepository;
	
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	@GetMapping("/getAllUnits")
	@ResponseBody
	public List<UnitEntity> getAllDistributors(Model m, HttpSession session ) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		
	    List<UnitEntity> listOfUnit = unitRepository.findByTenantId(tenantId);

	    return listOfUnit;
	}

}
