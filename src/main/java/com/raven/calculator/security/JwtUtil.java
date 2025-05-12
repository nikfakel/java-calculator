package com.raven.calculator.security;

import io.github.cdimascio.dotenv.Dotenv;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.util.Date;
import javax.crypto.SecretKey;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
    @Getter
    private final SecretKey jwtSecretKey;
    private final long jwtExpirationMs;

    public JwtUtil() {
        Dotenv dotenv = Dotenv.configure().load();

        String secret = dotenv.get("JWT_SECRET");
        this.jwtSecretKey = Keys.hmacShaKeyFor(secret.getBytes());
        this.jwtExpirationMs = Long.parseLong(dotenv.get("JWT_EXPIRATION"));
    }

    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + jwtExpirationMs))
                .signWith(jwtSecretKey)
                .compact();
    }
}