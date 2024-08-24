package com.erp.adminController;

import static org.hamcrest.CoreMatchers.nullValue;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.entity.User;
import com.erp.dto.UserDOT;
import com.erp.repository.UserRepository;
import com.erp.service.UserService;

import jakarta.servlet.http.HttpSession;


@Controller
public class HomeController {

	
	@Autowired
	private UserService userService;

	@Autowired
	private UserRepository userRepo;



	@GetMapping("/")
	public String index() {
		return "index";
	}

	@GetMapping("/register")
	public String register() {
		return "register";
	}

	@GetMapping("/signin")
	public String login() {
		return "login";
	}

	/*
	 * @GetMapping("/user/profile") public String profile(Principal p, Model m) {
	 * String email = p.getName(); User user = userRepo.findByEmail(email);
	 * m.addAttribute("user", user); return "profile"; }
	 * 
	 * @GetMapping("/user/home") public String home() { return "home"; }
	 */

	@PostMapping("/saveUser")
	public String saveUser(@ModelAttribute User newuser, HttpSession session) {
		
		 try {
	            userService.saveUser(newuser);
	            session.setAttribute("message", "Account created successfully! Please log in.");
	        } catch (IllegalStateException e) {
	        	session.setAttribute("message", e.getMessage());
	            return "register"; 
	        }
	        return "register";
	    
	}
	@PostMapping("/admin/saveUser")
	@ResponseBody
	public Void saveStudent(UserDOT user) {

		 System.out.println(user);

		 userService.saveStudent(user);
		return null;

		
	}
	

}
