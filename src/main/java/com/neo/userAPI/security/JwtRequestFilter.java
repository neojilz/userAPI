package com.neo.userAPI.security;

import com.neo.userAPI.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

    @Value("${jwt.secret}")
    String SECRET_KEY;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {


        String jsonToken = "";
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer")) {
            jsonToken = authorizationHeader.substring(7);
            boolean isValidToken = jwtTokenUtil.validateToken(jsonToken);

        if(isValidToken){
            Authentication authentication = jwtTokenUtil.getAuthentication(jsonToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }else {
            System.out.println(request + "REQUEST UNAUTHENTICATED");
        }

        }




        // Even /authenticate call wont work without this line!!!
        filterChain.doFilter(request,response);

    }
}
