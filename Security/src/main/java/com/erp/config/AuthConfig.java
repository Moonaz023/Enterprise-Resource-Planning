package com.erp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class AuthConfig {
	
	@Bean
    public UserDetailsService userDetailsService(){
        return new CustomUserDetailsService();//2. have to make a custom user 
    }
	
	@Bean
    public AuthenticationProvider authenticationProvider(){// 5.
        DaoAuthenticationProvider authenticationProvider=new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }
	
	

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
		.csrf((csrf) -> csrf.disable())
        .authorizeHttpRequests((authorize) -> { 

        	//authorize.requestMatchers("/auth/register", "/auth/token", "/auth/validate")
			authorize.requestMatchers("/auth/register","/signup", "/auth/token", "/auth/validate", "/auth/loginpage", "/auth/abc", "/auth/loginpage2","/","/templates/**","/auth/logout")
			.permitAll();
			 authorize.anyRequest().authenticated();
        	/*authorize
            .anyRequest().permitAll();*/
		});

		return http.build();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();		
	}
	
	@Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

}
