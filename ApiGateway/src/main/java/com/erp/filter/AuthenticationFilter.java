package com.erp.filter;

import org.springframework.stereotype.Component;

import com.erp.util.JwtUtil;

import reactor.core.publisher.Mono;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
//import org.springframework.web.client.RestTemplate;

/*@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    //    @Autowired
//    private RestTemplate template;
    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
    	//System.out.println("Came here---------->");
        return ((exchange, chain) -> {
            if (validator.isSecured.test(exchange.getRequest())) {
                //header contains token or not
                if (!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                    throw new RuntimeException("missing authorization header");
                }

                String authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);
                if (authHeader != null && authHeader.startsWith("Bearer ")) {
                	System.out.println("Token---------->"+authHeader);
                    authHeader = authHeader.substring(7);
                }
                try {
//                    //REST call to AUTH service
//                    template.getForObject("http://SECURITY//validate?token" + authHeader, String.class);
                    jwtUtil.validateToken(authHeader);

                } catch (Exception e) {
                    System.out.println("invalid access...!");
                    throw new RuntimeException("un authorized access to application");
                }
            }
            return chain.filter(exchange);
        });
    }

    public static class Config {

    }
}*/
import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import com.erp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpStatus;

import org.springframework.http.HttpCookie;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import com.erp.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.http.HttpStatus;
import reactor.core.publisher.Mono;

@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator validator;

    @Autowired
    private JwtUtil jwtUtil;

    public AuthenticationFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
        return (exchange, chain) -> {
        	ServerHttpRequest request = null;
            String requestPath = exchange.getRequest().getURI().getPath();
            
            // Prevent logged-in users from accessing the login page
            if ("/auth/loginpage2".equals(requestPath)) {
                String authHeader = extractAuthToken(exchange);

                if (authHeader != null) {
                    try {
                        // Validate the token
                        jwtUtil.validateToken(authHeader);
                        // If valid, redirect logged-in users away from login page
                        return redirectToHomePage(exchange);
                    } catch (Exception e) {
                        // If token is invalid, proceed to login page
                        System.out.println("Invalid token, proceed to login page");
                    }
                }
            }

            // Existing authentication logic for secured routes
            if (validator.isSecured.test(exchange.getRequest())) {
                String authHeader = extractAuthToken(exchange);

                if (authHeader == null) {
                    return redirectToLoginPage(exchange); // Missing token, redirect to login
                }

                try {
                    // Validate the token
                    jwtUtil.validateToken(authHeader);
                     request = exchange.getRequest()
                    .mutate()
                    .header("tenantId", String.valueOf(jwtUtil.extractUserId(authHeader)))
                    .build();
                } catch (Exception e) {
                    System.out.println("Invalid access...!");
                    return redirectToLoginPage(exchange); // Invalid token, redirect to login
                }
            }

            return chain.filter(exchange.mutate().request(request).build());
        };
    }

    // Extracts the authorization token from either the header or the cookie
    private String extractAuthToken(ServerWebExchange exchange) {
        String authHeader = null;

        // Check if Authorization header is present
        if (exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
            authHeader = exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION).get(0);

            if (authHeader != null && authHeader.startsWith("Bearer ")) {
                authHeader = authHeader.substring(7); // Remove "Bearer " prefix
                System.out.println("Token received by Header=====>" + authHeader);
            }
        } else if (exchange.getRequest().getCookies().containsKey("JWT-TOKEN")) {
            // Check if JWT-TOKEN cookie is present if Authorization header is missing
            authHeader = exchange.getRequest().getCookies().getFirst("JWT-TOKEN").getValue();
            System.out.println("Token received by Cookie=====>" + authHeader);
        }

        return authHeader;
    }

    // Method to handle redirection to the login page
    private Mono<Void> redirectToLoginPage(ServerWebExchange exchange) {
        if (!exchange.getRequest().getURI().getPath().equals("/auth/loginpage2")) {
            exchange.getResponse().setStatusCode(HttpStatus.FOUND); // 302 redirect
            exchange.getResponse().getHeaders().setLocation(URI.create("/auth/loginpage2")); // Set location to login page
        }
        return exchange.getResponse().setComplete(); // Complete the response
    }

    // Method to handle redirection to the home/dashboard page if already logged in
    private Mono<Void> redirectToHomePage(ServerWebExchange exchange) {
        exchange.getResponse().setStatusCode(HttpStatus.FOUND); // 302 redirect
        exchange.getResponse().getHeaders().setLocation(URI.create("/products/admin/product")); // Redirect to the dashboard
        return exchange.getResponse().setComplete(); // Complete the response
    }

    public static class Config {
    }
}
