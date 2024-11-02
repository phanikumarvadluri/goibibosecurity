package com.gogibibo.goibibo.service;


import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtService {


    private String secretKey="";


    public JwtService() throws NoSuchAlgorithmException {
        KeyGenerator keyGenerator=KeyGenerator.getInstance("HmacSHA256");
        SecretKey secretKey1 = keyGenerator.generateKey();
        secretKey= Base64.getEncoder().encodeToString(secretKey1.getEncoded());
    }


    public String genetateToken(String username) {
        Map<String,Object> claims=new HashMap<>();

        return Jwts.builder().claims().add(claims).subject(username).issuedAt(new Date(System.currentTimeMillis())).expiration(new Date(System.currentTimeMillis()+60*60*30)).and()
                .signWith(getKey()).compact();

    }

    private SecretKey getKey() {
       byte[] key= Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(key);
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims :: getSubject);
    }

    private<T> T extractClaim(String token, Function<Claims,T> claimsResolver) {
        final Claims claims=extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token).getPayload();
    }

    public boolean validateToken(String token, UserDetails userDetails) {
        String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())&& !isTokenExpire(token));
    }

    private boolean isTokenExpire(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token,Claims::getExpiration);
    }
}
