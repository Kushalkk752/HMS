package com.hms.configuration;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JWTFilter extends OncePerRequestFilter {
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        String token = request.getHeader("Authorization");
        System.out.println(token);
        if(token!=null && token.startsWith("Bearer ")){
            String tokenVal = token.substring(8, token.length() - 1);
            System.out.println(tokenVal);
        }
    }
    //if a user is logged in for first time he get's a token
    //if he logs in for the second time he has to send token in the header of the hhtp file


}
