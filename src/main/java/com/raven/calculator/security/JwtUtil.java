package com.raven.calculator.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtUtil {
    private static final Logger logger = LoggerFactory.getLogger(JwtUtil.class);

    @Getter
    private final SecretKey jwtSecretKey;

    public JwtUtil() {
        SignatureAlgorithm algorithm = SignatureAlgorithm.HS512;
        logger.info("Using Signature Algorithm: {}", algorithm.getJcaName());

        // Generate a 256-bit key (32 bytes)
        String secret = "a-string-secret-at-least-256-bits-long";
        this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String generateToken(String username) {
        // 1 day
        long jwtExpirationMs = 86400000;

        logger.info("jwtSecretKey: {}", jwtSecretKey);


        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }
}