package com.slicemate.apigateway.filter;

import com.slicemate.apigateway.Util.JwtUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.util.Objects;
import java.util.Set;

@Slf4j
@Component
public class AuthenticationFilter extends AbstractGatewayFilterFactory<AuthenticationFilter.Config> {

    @Autowired
    private RouteValidator routeValidator;

    @Autowired
    private JwtUtil jwtutil;

    public AuthenticationFilter() {
        super(Config.class);
        System.out.println("Inside AuthenticationFilter constructor");
    }


    @Override
    public GatewayFilter apply(Config config) {
        return ((exchange, chain) -> {
           if(routeValidator.isSecured.test(exchange.getRequest())) {
               if(!exchange.getRequest().getHeaders().containsKey(HttpHeaders.AUTHORIZATION)) {
                   throw new RuntimeException("Authorization header not present");
               }

               String authHeader = Objects.requireNonNull(exchange.getRequest().getHeaders().get(HttpHeaders.AUTHORIZATION)).getFirst();
               if(authHeader != null && authHeader.startsWith("Bearer ")) {
                   authHeader = authHeader.substring(7);

               }
               try {
                   jwtutil.validateToken(authHeader);
                   System.out.println("token validated");
                   Set<String> roles = jwtutil.extractRoles(authHeader);
                   System.out.println("roles extracted");
                   System.out.println(roles);
                   if (!routeValidator.isAllowed(exchange.getRequest(), roles)) {
                       System.out.println("inside forbidden");
                       exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                       return exchange.getResponse().setComplete();
                   }
               } catch (Exception e) {
                   log.error("e: ", e);
                   exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                   return exchange.getResponse().setComplete();
               }
           }
           return chain.filter(exchange);
        });
    }

    public static class Config{

    }
}
