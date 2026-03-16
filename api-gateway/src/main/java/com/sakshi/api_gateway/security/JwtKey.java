package com.sakshi.api_gateway.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

public class JwtKey {

    private static final String SECRET = "your-very-secret-key-that-is-long-enough-123456";

    private static final SecretKey key =
            Keys.hmacShaKeyFor(SECRET.getBytes());

    public static Claims validateToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
