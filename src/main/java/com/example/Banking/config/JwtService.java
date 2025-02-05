package com.example.Banking.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    @Value("${application.security.jwt.secret-key}")
    private String secretKey;

    public String extractEmail(String token) {
        return extractClaim(token , Claims::getSubject);
    }

    public Boolean isTokenValid(String token , UserDetails user) {
        String email = extractEmail(token);
        return (email.equals(user.getUsername()) && !isTokenExpired(token));

    }

    public Date extractExpiration(String token ) {
        return extractClaim(token , Claims::getExpiration);
    }

    public <T> T extractClaim(String token , Function<Claims , T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return  claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.
                parserBuilder()
                .setSigningKey(getSigninKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    public Boolean isTokenExpired(String token) {
        return  extractExpiration(token).before(new Date());
    }

    public String  generateToken (UserDetails userDetails) {
        return buildToken(new HashMap<>() , userDetails);
    }

    public String generateToken (Map<String , Object> extraClaims , UserDetails userDetails) {
        return buildToken(extraClaims,  userDetails);
    }

    private String  buildToken(Map <String , Object> extraClaims , UserDetails userDetails) {
        return Jwts
                .builder()
                .setClaims(extraClaims)
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + (24 * 60 * 60 * 1000)))
                .signWith(getSigninKey() , SignatureAlgorithm.HS256)
                .compact();
    }

    private Key getSigninKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
