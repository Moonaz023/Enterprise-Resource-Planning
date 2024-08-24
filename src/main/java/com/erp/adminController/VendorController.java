package com.erp.adminController;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.erp.dto.VendorHistoryDTO;
import com.erp.entity.VendorEntity;
import com.erp.service.VendorService;

import jakarta.servlet.http.HttpSession;
@Controller
@RequestMapping("/admin")
public class VendorController {
	
		@Autowired
	    private VendorService vendorService;
		
		@ModelAttribute
		public void commonUser(Principal p, Model user) {
			if (p != null) {
				String name = p.getName();

				user.addAttribute("user", name);
			}
		}
		
		@GetMapping("/vendor")
		public String VendorIndex() {
			return "Vendors";
		}
		@PostMapping("/saveVendor")
		@ResponseBody
		public String saveVendor(@ModelAttribute VendorEntity vendor , HttpSession session ) {
			Long tenantId = (Long) session.getAttribute("tenantId");
			System.out.println(vendor);
			vendorService.saveVendor(vendor,tenantId);
			return "Vendor saved";
		}
		@GetMapping("/getAllVendors")
		@ResponseBody
		public List<VendorEntity> getAllVendors(Model m, HttpSession session ) {
			Long tenantId = (Long) session.getAttribute("tenantId");
			
		    List<VendorEntity> listOfvendor = vendorService.getAllVendor(tenantId);

		    return listOfvendor;
		}
		@DeleteMapping("/deleteVendor")
		@ResponseBody
		public String deleteVendor(@RequestParam("id") long id) {
			vendorService.deleteVendor(id);
		    return "Vendor deleted successfully";
		}
		@PostMapping("/updateVendor")
	    @ResponseBody
	    public String updateVendor( @ModelAttribute VendorEntity updatedVendor, HttpSession session ) {
			Long tenantId = (Long) session.getAttribute("tenantId");
			vendorService.updateVendor(updatedVendor,tenantId);

	        return "Vendor record updated successfully";
	    }
		
		@GetMapping("/VendorProfile/{id}")
		public String distributorProfile(@PathVariable Long id, Model model) {
			VendorHistoryDTO distributorHistory = vendorService.vendorProfile(id);
		    model.addAttribute("distributorHistory", distributorHistory);
		    return "VendorProfile";
		}

		@GetMapping("/VendorHistory/{id}")
		@ResponseBody
		public VendorHistoryDTO VendorHistory(@PathVariable Long id)
		{
			return vendorService.vendorProfile(id);
			
		}
		
	}
