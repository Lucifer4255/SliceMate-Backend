package com.slicemate.apigateway.filter;

import org.springframework.http.HttpMethod;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.function.Predicate;

@Component
public class RouteValidator {

    // Open API endpoints that do not require authentication
    // Map of path patterns to allowed HTTP methods
    private static final Map<String, Set<HttpMethod>> openApiEndpoints = Map.of(
            "/auth/register", Set.of(HttpMethod.POST),
            "/auth/login", Set.of(HttpMethod.POST),
            "/auth/oauth2/google/callback", Set.of(HttpMethod.GET),
            "/food", Set.of(HttpMethod.GET), // Only GET allowed for /food
            "/food/*", Set.of(HttpMethod.GET), // Only GET allowed for /food/*
            "/food/category/*", Set.of(HttpMethod.GET), // Only GET allowed for /food/category
            "/food/exists/*", Set.of(HttpMethod.GET) // Only GET allowed for /food/exists
    );

    // Special key to represent all HTTP methods
    private static final String ALL_METHODS = "ALL";

    // Role-specific endpoints with HTTP methods
    private static final Map<String, Map<String, List<String>>> roleSpecificEndpoints = Map.of(
            "ADMIN", Map.of(
                    ALL_METHODS, List.of("/**") // Allow ALL methods on ALL routes
            ),
            "USER", Map.of(
                    ALL_METHODS, List.of("/cart/**", "/orders/**", "/payments/**")
            )
    );

    // Predicate to check if the request is to a secured endpoint
    public Predicate<ServerHttpRequest> isSecured = request -> {
        String path = request.getURI().getPath();
        HttpMethod method = request.getMethod();

        // Check if the request matches any open endpoint
        for (Map.Entry<String, Set<HttpMethod>> entry : openApiEndpoints.entrySet()) {
            String pattern = entry.getKey();
            Set<HttpMethod> allowedMethods = entry.getValue();

            if (matchesPattern(pattern, path) && allowedMethods.contains(method)) {
                return false; // Request is to an open endpoint
            }
        }

        return true; // Request is to a secured endpoint
    };

    // Method to check if the request is allowed for the given roles
    public boolean isAllowed(ServerHttpRequest request, Set<String> roles) {
        String path = request.getURI().getPath();
        String method = request.getMethod().name();

        // Check if the path and method match any role-specific endpoint pattern
        for (String role : roles) {
            if (roleSpecificEndpoints.containsKey(role)) {
                Map<String, List<String>> methodMap = roleSpecificEndpoints.get(role);

                // Check for ALL methods
                if (methodMap.containsKey(ALL_METHODS)) {
                    for (String pattern : methodMap.get(ALL_METHODS)) {
                        if (matchesPattern(pattern, path)) {
                            return true;
                        }
                    }
                }

                // Check for specific HTTP method
                if (methodMap.containsKey(method)) {
                    for (String pattern : methodMap.get(method)) {
                        if (matchesPattern(pattern, path)) {
                            return true;
                        }
                    }
                }
            }
        }

        // If no role-specific endpoint matches, check if it's a secured endpoint
        return !isSecured.test(request);
    }

    // Helper method to match path patterns
    private boolean matchesPattern(String pattern, String path) {
        if (pattern.endsWith("/**")) {
            // Match any subpaths (e.g., /food/** matches /food/foo, /food/foo/bar)
            return path.startsWith(pattern.substring(0, pattern.length() - 3));
        } else if (pattern.endsWith("/*")) {
            // Match single-level dynamic paths (e.g., /food/* matches /food/1, /food/2)
            String basePath = pattern.substring(0, pattern.length() - 2);
            return path.startsWith(basePath) && path.substring(basePath.length()).split("/").length == 2;
        } else {
            // Exact match (e.g., /food matches /food)
            return path.equals(pattern);
        }
    }
}