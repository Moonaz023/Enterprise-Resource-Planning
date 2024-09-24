package com.erp.controller;

import com.erp.dto.AuthRequest;
import com.erp.entity.User;
import com.erp.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Controller
@RequestMapping("/auth")
public class AuthController2 {

    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;
    
    @GetMapping("/loginpage2")
    public String index()
    {
    	return "login";
    }

    @GetMapping("/loginsuccess")
    public String indccex(@RequestHeader("Authorization") String token)
    {
    	return "loginsuccess";
    }
    @PostMapping("/register")
    @ResponseBody
    public String addNewUser(@ModelAttribute User user) {
        return service.saveUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password,
                        Model model) {

        try {
            Authentication authenticate = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            if (authenticate.isAuthenticated()) {
                String token = service.generateToken(username);
                model.addAttribute("token", token);
                return "redirect:/products/admin/product";
            } else {
                model.addAttribute("error", "Invalid username or password");
                return "login";
            }

        } catch (Exception e) {
            model.addAttribute("error", "An error occurred during login");
            return "login";
        }
    }
}
