package com.motos.api.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public class JwtUtil {

    private static final String SECRET_KEY =
            "mi_clave_super_secreta_muy_larga_123456";

    public static String generateToken(String username) {

        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(
                        new Date(
                                System.currentTimeMillis()
                                        + 1000 * 60 * 60
                        )
                )
                .signWith(
                        SignatureAlgorithm.HS256,
                        SECRET_KEY.getBytes()
                )
                .compact();
    }

    public static String extractUsername(String token) {

        Claims claims = Jwts.parser()
                .setSigningKey(
                        SECRET_KEY.getBytes()
                )
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }
}