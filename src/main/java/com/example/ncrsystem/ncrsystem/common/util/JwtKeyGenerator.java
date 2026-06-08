package com.example.ncrsystem.ncrsystem.common.util;

import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.UUID;

public class JwtKeyGenerator {
    public static void main(String[] args) {
        SecretKey key = Keys.hmacShaKeyFor(
                UUID.randomUUID()
                        .toString()
                        .repeat(2)
                        .getBytes(StandardCharsets.UTF_8)
        );

        System.out.println(
                Base64.getEncoder().encodeToString(key.getEncoded())
        );
    }
}
