package com.example.slicemate.service;

import io.jsonwebtoken.Claims;

import java.util.Date;
import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    public String getUsernameFromToken(String token);
    public Date getExpirationDateFromToken(String token);
    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver);



    public String generateToken(String username);
    public String createToken(Map<String,Object> claims, String username);
}
