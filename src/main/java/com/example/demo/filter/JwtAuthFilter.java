package com.example.demo.filter;

import com.example.demo.model.User;
import com.example.demo.service.CustomUserDetailsService;
import io.jsonwebtoken.JwtException;
import jdk.jfr.Category;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import java.io.IOException;
import java.util.Optional;

import com.example.demo.token.JwtUtil;

@Component
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil ;
    private final CustomUserDetailsService customUserDetailsService;
    public JwtAuthFilter(JwtUtil jwtUtil, CustomUserDetailsService customUserDetailsService) {
        this.jwtUtil = jwtUtil;
        this.customUserDetailsService = customUserDetailsService;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filter) throws IOException, ServletException{
        String authheader = request.getHeader(HttpHeaders.AUTHORIZATION);
        String token = null;
        if (authheader != null && authheader.startsWith("Bearer ")) {
            token = authheader.substring(7);
        }
        if (token != null) {
            try {
                String username = jwtUtil.getUsername(token);
                UserDetails userDetails = customUserDetailsService.loadUserByUsername(username);
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
            catch (JwtException j) {
                System.out.println("Sorry please login again.");
            }
        }
        filter.doFilter(request, response);
    }
}
