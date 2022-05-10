package com.jb.CouponSystemProjectP2.Security;

import com.jb.CouponSystemProjectP2.Beans.LoginDetails;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.SignatureException;
import org.springframework.stereotype.Service;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.sql.Date;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

@Service
public class JWTutil {
    private String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private String secretKey = "yek+gnol+tib+2+xis+evif+6+owt+s+5+raas+5+dna+viva+6+si+siht+2";
    private Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(this.secretKey), this.signatureAlgorithm);

    public String generateToken(LoginDetails loginDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserType", loginDetails.getUserType());
        return createToken(claims, loginDetails.getEmail());
    }

    public String generateToken(String token) {
        Map<String, Object> newClaims = new HashMap<>();
        Claims oldClaims = extractAllClaims(token.replace("Bearer ", ""));
        newClaims.put("UserType", oldClaims.get("UserType"));
        return createToken(newClaims, oldClaims.getSubject());
    }

    public String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey)
                .compact();
    }

    public Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException{
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token.replace("Bearer ", "")).getBody();
    }

    public boolean isTokenValid(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException {
        final Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        return true;
    }

    public int getIdFromToken(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException{
        Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        return (int) claims.get("id");
    }
}
