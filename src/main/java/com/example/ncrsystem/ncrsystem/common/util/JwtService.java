package com.example.ncrsystem.ncrsystem.common.util;

import com.example.ncrsystem.ncrsystem.model.User;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


@Service
public class JwtService {
    @Value("${jwt.secret}")
    private String secret;
    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(
                Base64.getDecoder().decode(secret)
        );
    }
    public String generateToken(User user) {

        Map<String, Object> claims = new HashMap<>();

        claims.put("userId", user.getUserId());
        claims.put("fullname", user.getFullname());
        claims.put("email", user.getEmail());
        claims.put("position", user.getPosition());

        if (user.getRole() != null) {
            claims.put("roleId", user.getRole().getRoleId());
            claims.put("roleName", user.getRole().getRoleName());
        }

        if (user.getDepartment() != null) {
            claims.put("departmentId",
                    user.getDepartment().getDepartmentId());
            claims.put("departmentName",
                    user.getDepartment().getDepartmentName());
        }

        return Jwts.builder()
                .claims(claims)
                .subject(user.getUsername())
                .issuedAt(new Date())
                .expiration(
                        new Date(System.currentTimeMillis() + 86400000)
                )
                .signWith(getSigningKey())
                .compact();
    }

    public Claims extractAllClaims(String token) {

        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean validateToken(String token) {
        try {
            extractAllClaims(token);
            return true;
        } catch (Exception ex) {
            return false;
        }
    }

    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }
}
