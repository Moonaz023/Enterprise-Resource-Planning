package com.erp.adminController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import com.erp.entity.UnitEntity;
import com.erp.repository.UnitRepository;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class UnitController {
	@Autowired
	private UnitRepository unitRepository;
	
	@GetMapping("/unitpage")
	public String unitpage(){
		return "unitpage";
	}


	@PostMapping("/saveNewUnit")
	@ResponseBody
	public void saveNewUnit(@ModelAttribute UnitEntity newUnit,  @RequestHeader("tenantId") String tenant  ) {
		Long tenantId = Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");

		newUnit.setTenantId(tenantId);
		unitRepository.save(newUnit);

	}


	@GetMapping("/getAllUnits")
	@ResponseBody
	public List<UnitEntity> getAllDistributors(Model m,  @RequestHeader("tenantId") String tenant  ) {
		Long tenantId = Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");
		
	    List<UnitEntity> listOfUnit = unitRepository.findByTenantId(tenantId);

	    return listOfUnit;
	}

	@GetMapping("/getUnitById")
	@ResponseBody
	public UnitEntity getUnitById(@RequestParam("id") long id)
	{
		Optional<UnitEntity> unitEntityOptional =unitRepository.findById(id);
		UnitEntity unit =unitEntityOptional.get();
		return unit;
	}


	@DeleteMapping("/deleteUnit")
	@ResponseBody
	public void deleteUnit(@RequestParam("id") long id) {
		unitRepository.deleteById(id);

	}


	@PutMapping("/updateUnit")
	@ResponseBody
	public void updateUnit(@ModelAttribute UnitEntity updatedUnit ) {

		unitRepository.update(updatedUnit.getName(),updatedUnit.getId());

	}

}
