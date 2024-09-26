package com.erp.service;

import com.erp.entity.User;

public interface AuthService {

	public String saveUser(User user);
	public String generateToken(User authenticateUser);

    public void validateToken(String token) ;


}
