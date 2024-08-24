package com.erp.service;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;


import com.erp.entity.User;
import com.erp.dto.UserDOT;
import com.erp.repository.UserRepository;


import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepo;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Override
	public User saveUser(User user) {

		/*String password=passwordEncoder.encode(user.getPassword());
		user.setPassword(password);
		user.setRole("ROLE_ADMIN");
		
		User newuser = userRepo.save(user);
		
		return newuser;*/
		// Check if the email already exists
	    Optional<User> existingUser = Optional.ofNullable(userRepo.findByEmail(user.getEmail()));
	    if (existingUser.isPresent()) {
	        throw new IllegalStateException("This email is already registered. Please log in instead.");
	    }

	    // Encode the password
	    String password = passwordEncoder.encode(user.getPassword());
	    user.setPassword(password);
	    user.setRole("ROLE_ADMIN");
	    
	    // Save the new user
	    return userRepo.save(user);
	}

	@Override
	public void removeSessionMessage() {

		HttpSession session = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes())).getRequest()
				.getSession();

		session.removeAttribute("msg");
	}

	@Override
	public void saveStudent(UserDOT userDOT) {
		String password=passwordEncoder.encode(userDOT.getPassword());
		String email=userDOT.getEmail();
		String name=userDOT.getName();
		
		User user=new User();
		user.setEmail(email);
		user.setName(name);
		user.setPassword(password);
		user.setRole("ROLE_USER");
		
		userRepo.save(user);

		
		
	}

	

	
}
