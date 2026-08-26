package com.example.demo.tests;

import java.util.*;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import io.jsonwebtoken.Jwt;

import javax.crypto.SecretKey;
public class testTokens {
    private final SecretKey key = Jwts.SIG.HS256.key().build();
    public String generateToken(String username) {
        Date date = new Date();
        Date expirydate = new Date(date.getTime()+3600000);
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        testTokens j = new testTokens();
        System.out.println(j.generateToken(s));
        System.out.println(j.getUsername(j.generateToken(s)));
    }
}
