package com.erp.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Autowired
	public CustomAuthSucessHandler sucessHandler;

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Bean
	public UserDetailsService getDetailsService() {
		return new CustomUserDetailsService();
	}

	@Bean
	public DaoAuthenticationProvider getAuthenticationProvider() {
		DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
		daoAuthenticationProvider.setUserDetailsService(getDetailsService());
		daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
		return daoAuthenticationProvider;
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

	    http.csrf(csrf -> csrf.disable())
	        .authorizeHttpRequests(authorize -> {
	            authorize.requestMatchers("/user/**").hasRole("USER");
	            authorize.requestMatchers("/admin/**").hasRole("ADMIN");
	            authorize.requestMatchers("/**").permitAll();
	            authorize.anyRequest().authenticated();
	        })
	        .formLogin(formLogin -> formLogin.loginPage("/signin")
	            .loginProcessingUrl("/userLogin")
	            .successHandler(sucessHandler)
	            .permitAll())
	        .logout(logout -> logout.logoutUrl("/logout").logoutSuccessUrl("/").permitAll())
	        .addFilterBefore(new RedirectLoggedInUserFilter(), UsernamePasswordAuthenticationFilter.class);

	    return http.build();
	}

	
	public class RedirectLoggedInUserFilter extends GenericFilterBean {
	    @Override
	    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
	            throws IOException, ServletException {
	        HttpServletRequest httpRequest = (HttpServletRequest) request;
	        HttpServletResponse httpResponse = (HttpServletResponse) response;
	        
	        if ((httpRequest.getRequestURI().equals("/signin") || httpRequest.getRequestURI().equals("/register"))&& SecurityContextHolder.getContext().getAuthentication() != null) {
	            httpResponse.sendRedirect("/admin/product"); 
	        } else {
	            chain.doFilter(request, response);
	        }
	    }
	}

	

}
