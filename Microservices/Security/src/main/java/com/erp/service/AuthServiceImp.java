package com.erp.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.erp.entity.User;
import com.erp.repository.UserRepository;

@Service
public class AuthServiceImp implements AuthService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private JwtService jwtService;

	@Override
	public String saveUser(User user) {
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		userRepository.save(user);
		return "user added to the system";
	}

	@Override
	public String generateToken(String username) {
		return jwtService.generateToken(username);
	}

	@Override
	public void validateToken(String token) {
		jwtService.validateToken(token);
	}

}
