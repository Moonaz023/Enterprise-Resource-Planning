package com.erp.filter;

import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    public static final List<String> openApiEndpoints = List.of(
            "/auth/register",
            "/auth/loginpage2",
            "/auth/token",
            "/signup",
            "/eureka",
            "/" 
    );

    public Predicate<ServerHttpRequest> isSecured =
            request -> openApiEndpoints
                    .stream()
                    .noneMatch(uri -> isExactMatch(uri, request.getURI().getPath()));

    private boolean isExactMatch(String uri, String path) {
        
        if ("/".equals(uri)) {
            return "/".equals(path);  
        }
        
        return path.startsWith(uri);
    }
}