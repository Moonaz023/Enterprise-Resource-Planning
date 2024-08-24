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

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;
import com.erp.service.DistributorService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class DistributorController {

	@Autowired
    private DistributorService distributorService;
	
	@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}
	
	
	@GetMapping("/distributor")
	public String index() {
		return "Distributor";
	}
	@PostMapping("admin/saveDistributor")
	public String saveProduct(@ModelAttribute DistributorEntity distributor , HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		System.out.println(distributor);
		distributorService.saveDistributor(distributor,tenantId);
		return "redirect:/";
	}
	@GetMapping("/getAllDistributors")
	@ResponseBody
	public List<DistributorEntity> getAllDistributors(Model m, HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
		
	    List<DistributorEntity> listOfdistributor = distributorService.getAlldistributor(tenantId);

	    return listOfdistributor;
	}
	@DeleteMapping("/deleteDistributor")
	@ResponseBody
	public String deleteDistributor(@RequestParam("id") long id) {
		distributorService.deleteDistributor(id);
	    return "Distributor deleted successfully";
	}
	@PostMapping("/updateDistributor")
    @ResponseBody
    public String updateDistributor( @ModelAttribute DistributorEntity updatedDistributor, HttpSession session) {
		Long tenantId = (Long) session.getAttribute("tenantId");
        
		distributorService.updateDistributor(updatedDistributor,tenantId);

        return "Distributor record updated successfully";
    }
	@GetMapping("/DistributorProfile/{id}")
	public String distributorProfile(@PathVariable Long id, Model model) {
	    DistributorHistoryDTO distributorHistory = distributorService.distributorProfile(id);
	    model.addAttribute("distributorHistory", distributorHistory);
	    return "DistributorProfile";
	}

	@GetMapping("/DistributorHistory/{id}")
	@ResponseBody
	public DistributorHistoryDTO DistributorHistory(@PathVariable Long id)
	{
		return distributorService.distributorProfile(id);
		
	}
}
