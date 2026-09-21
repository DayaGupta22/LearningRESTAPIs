package com.TechieSpring.LearningRESTAPis.services;

import com.TechieSpring.LearningRESTAPis.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.Set;

@Service
public class JwtService {

    @Value("${jwt.secretKey}")
    private String jwtSceretkey;

    private SecretKey getSecretKey(){
        return Keys.hmacShaKeyFor(jwtSceretkey.getBytes(StandardCharsets.UTF_8));
    }

    public String generateToken(User user){
        Date issuedAt = new Date();

        Date expiration = new Date(
                System.currentTimeMillis() + 1000L * 60 * 10
        );

        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
                .claim("roles", Set.of("ADMIN", "USER"))
                .issuedAt(issuedAt)
                .expiration(expiration)
                .signWith(getSecretKey())
                .compact();
    }

    public Long getUserIdFromtoken(String token){
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
        return Long.valueOf(claims.getSubject());
    }

}
