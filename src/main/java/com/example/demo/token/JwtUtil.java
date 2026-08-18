package com.example.demo.token;

import java.util.*;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import java.util.*;
import io.jsonwebtoken.security.Keys;
import org.hibernate.type.descriptor.java.LocalDateJavaType;

import javax.crypto.SecretKey;
public class JwtUtil {
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
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.next();
        JwtUtil j = new JwtUtil();
        System.out.println(j.generateToken(s));
    }
}
