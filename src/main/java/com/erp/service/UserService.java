package com.erp.service;



import com.erp.entity.User;
import com.erp.dto.UserDOT;

public interface UserService {

	public User saveUser(User user);

	public void removeSessionMessage();

	void saveStudent(UserDOT user);

	

}
