package com.example.ncrsystem.ncrsystem.common.util;

import com.example.ncrsystem.ncrsystem.common.response.ApiResponse;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import tools.jackson.databind.ObjectMapper;

import java.io.IOException;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final ObjectMapper objectMapper;

    public JwtAuthenticationFilter(JwtService jwtService, ObjectMapper objectMapper) {
        this.jwtService = jwtService;
        this.objectMapper = objectMapper;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        String path = request.getRequestURI();

        if (path.startsWith("/api/auth")) {
            filterChain.doFilter(request, response);
            return;
        }

        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writeResponse(
                    response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Missing Authorization Header"
            );
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtService.validateToken(token)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            writeResponse(
                    response,
                    HttpServletResponse.SC_UNAUTHORIZED,
                    "Invalid Token"
            );
            return;
        }

        Claims claims = jwtService.extractAllClaims(token);
        System.out.println("Token Payload : "+ claims);

        request.setAttribute("claims", claims);
        request.setAttribute("userId", claims.get("userId"));
        request.setAttribute("username", claims.getSubject());

        filterChain.doFilter(request, response);
    }

    private void writeResponse(
            HttpServletResponse response,
            int status,
            String message
    ) throws IOException {

        ApiResponse<Object> apiResponse =
                new ApiResponse<>(
                        status,
                        message,
                        null
                );

        response.setStatus(status);
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        response.getWriter().write(
                objectMapper.writeValueAsString(apiResponse)
        );
    }
}