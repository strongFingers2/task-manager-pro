package com.example.demo.token;

import java.util.*;
import io.jsonwebtoken.Jwts;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
@Component
public class JwtUtil {
    private final SecretKey key = Jwts.SIG.HS256.key().build();

    public String generateToken(String username) {
        Date date = new Date();
        Date expirydate = new Date(date.getTime() + 3600000);
        String jwt = Jwts.builder()
                .header()
                .keyId("HS256")
                .and()
                .subject(username)
                .expiration(expirydate)
                .signWith(key)
                .compact();
        return jwt;
    }

    public String getUsername(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token)
                .getPayload()
                .getSubject();
    }
}