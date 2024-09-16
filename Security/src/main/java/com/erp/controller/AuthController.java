package com.erp.controller;

import com.erp.dto.AuthRequest;
import com.erp.entity.User;
import com.erp.service.AuthService;

import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
//@Controller
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService service;

    @Autowired
    private AuthenticationManager authenticationManager;

    
    
    @GetMapping("/abc")
    public String insex()
    {
    	return "abcHTMLPage";
    }
    @PostMapping("/register")
    public String addNewUser(@RequestBody User user) {
        return service.saveUser(user);
    }

    @PostMapping("/token")
    public String getToken(@RequestBody AuthRequest authRequest, HttpServletResponse response) {//1.authenticationManager can't communicate with DB directly so have to create bean in config
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));
        if (authenticate.isAuthenticated()) {
        	
        	String jwtToken=service.generateToken(authRequest.getUsername());
        	// Manually set the SameSite=None attribute in the Set-Cookie header
            response.setHeader("Set-Cookie", String.format(
                "JWT-TOKEN=%s; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=%d", 
                jwtToken, 60 * 60 // 1 hour expiration
            ));
            return jwtToken;
        } else {
            throw new RuntimeException("invalid access");
        }
    }
    
    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletResponse response) {
        // Clear the JWT token from the cookie by setting Max-Age=0
    	System.out.println("here----1");
        response.setHeader("Set-Cookie", String.format(
            "JWT-TOKEN=; HttpOnly; Secure; SameSite=None; Path=/; Max-Age=0"
        ));
        System.out.println("here----2");
        // Return a 200 OK status indicating successful logout
        return ResponseEntity.ok("Logged out successfully");
    }


    @GetMapping("/validate")
    public String validateToken(@RequestParam("token") String token) {
        service.validateToken(token);
        return "Token is valid";
    }
}
