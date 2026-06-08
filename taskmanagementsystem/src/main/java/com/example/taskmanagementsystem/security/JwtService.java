package com.example.taskmanagementsystem.security;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
@Service
public  class JwtService
{
    private static final String SECRET =
            "mysecretkeymysecretkeymysecretkey12345";

    private final SecretKey key= Keys.hmacShaKeyFor(SECRET.getBytes());


    public String generateKey(String email,String role)
    {
        return Jwts.builder()
               .subject(email)
               .claim("role",role)
               .issuedAt(new Date())
               .expiration(new Date(System.currentTimeMillis() //System.currentTimeMillis()
                                          +1000*60*60))
               .signWith(key)
               .compact();
    }
    public String ExtractEmail(String token)
    {
        return Jwts.parser()
               .verifyWith(key)
               .build()
               .parseSignedClaims(token)
               .getPayload()
               .getSubject();
    }
    public boolean isTokenValid(String token)
    {
        try{
            Jwts.parser()
            .verifyWith(key)
            .build()
            .parseSignedClaims(token);
            return true;
        }
        catch(Exception e)
        {
            return false;
        }
    }public String ExtractRole(String token)
    {
        return Jwts.parser()
        .verifyWith(key)
        .build()
        .parseSignedClaims(token)
        .getPayload()
        .get("role",String.class);
    }
}