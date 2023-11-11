package com.dev.LendingIntegration_.service.serviceImpl;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class GetAuthorizationTokenServiceImpl {

    @Value("${jwt.secret}")
    private String jwtSecret;

    @Value("${jwt.expirationInMinutes}")
    private int jwtExpirationInMinutes;

    public String generateToken(String username){
        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + jwtExpirationInMinutes);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);
        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.ES512, jwtSecret)
                .compact();
    }
    public Boolean validateToken(String token){
        try {
            Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token);
            return true;
        }catch (Exception e){
            return false;
        }
    }

    public String extractUsername(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.get("sub", String.class);
}
}
