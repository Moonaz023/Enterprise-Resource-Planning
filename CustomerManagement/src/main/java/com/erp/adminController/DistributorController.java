package com.erp.adminController;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.dto.DistributorHistoryDTO;
import com.erp.entity.DistributorEntity;
import com.erp.repository.DistributorRepository;
import com.erp.service.DistributorService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Cookie;
@Controller
@RequestMapping("/admin")
public class DistributorController {

	@Autowired
    private DistributorService distributorService;
	@Autowired
	private DistributorRepository distributorRepository;
	
	/*@ModelAttribute
	public void commonUser(Principal p, Model user) {
		if (p != null) {
			String name = p.getName();

			user.addAttribute("user", name);
		}
	}*/
	
	
	@GetMapping("/distributor")
	public String index() {
		return "Distributor";
	}
	@PostMapping("/saveDistributor")
	@ResponseBody
	public String saveProduct(@ModelAttribute DistributorEntity distributor , HttpSession session ,@RequestHeader("Authorization") String token, @RequestHeader("tenantId") String tenant ) {
		Long tenantId =  Long.parseLong(tenant);// (Long) session.getAttribute("tenantId");
		System.out.println(distributor);
		distributorService.saveDistributor(distributor,tenantId);
		return "Distributor added";
	}
	@GetMapping("/getAllDistributors")
	@ResponseBody
	public List<DistributorEntity> getAllDistributors(Model m, HttpSession session, @RequestHeader("Authorization") String token, @RequestHeader("tenantId") String tenant ) {
		
		//System.out.println("Tanant id got successfully===========>>>"+tenant);
		
		//Long tenantId = (Long) session.getAttribute("tenantId");
		Long tenantId =Long.parseLong(tenant);
	    List<DistributorEntity> listOfdistributor = distributorService.getAlldistributor(tenantId);

	    return listOfdistributor;
	}
	@DeleteMapping("/deleteDistributor")
	@ResponseBody
	public String deleteDistributor(@RequestParam("id") long id, @RequestHeader("Authorization") String token) {
		distributorService.deleteDistributor(id);
	    return "Distributor deleted successfully";
	}
	@PostMapping("/updateDistributor")
    @ResponseBody
    public String updateDistributor( @ModelAttribute DistributorEntity updatedDistributor, HttpSession session, @RequestHeader("Authorization") String token, @RequestHeader("tenantId") String tenant) {
		Long tenantId =  Long.parseLong(tenant);//(Long) session.getAttribute("tenantId");
        
		distributorService.updateDistributor(updatedDistributor,tenantId);

        return "Distributor record updated successfully";
    }
	@GetMapping("/DistributorProfile/{id}")
	public String distributorProfile(@PathVariable Long id, Model model,HttpServletRequest request) {
		String token =getTokenFromCookies(request);
	    DistributorHistoryDTO distributorHistory = distributorService.distributorProfile(id,token);
	    model.addAttribute("distributorHistory", distributorHistory);
	    return "DistributorProfile";
	}
	 private String getTokenFromCookies(HttpServletRequest request) {
	        Cookie[] cookies = request.getCookies();
	        if (cookies != null) {
	            for (Cookie cookie : cookies) {
	                if ("JWT-TOKEN".equals(cookie.getName())) {
	                    return cookie.getValue(); 
	                }
	            }
	        }
	        throw new RuntimeException("JWT-TOKEN cookie not found");
	    }
	

	@GetMapping("/DistributorHistory/{id}")
	@ResponseBody
	public DistributorHistoryDTO DistributorHistory(@PathVariable Long id, @RequestHeader("Authorization") String token)
	{
		return distributorService.distributorProfile(id,token);
		
	}
	
	
	
	
	
	
	
	@GetMapping("/findDistributorByOrderId")
	@ResponseBody
	DistributorEntity findDistributorByOrderId(@RequestParam("distributor_id") long distributor_id, @RequestHeader("Authorization") String token)
	{
		Optional<DistributorEntity> distributoropOptional=distributorRepository.findById(distributor_id);
		DistributorEntity distributor=distributoropOptional.get();
		return distributor;
	}
	@PostMapping("/saveUpdate")
	@ResponseBody
	void saveUpdate(@RequestBody DistributorEntity distributor, @RequestHeader("Authorization") String token)
	{
		distributorRepository.save(distributor);
	}
}
