package com.jb.CouponSystemProjectP2.Security;

import com.jb.CouponSystemProjectP2.Beans.LoginDetails;
import com.jb.CouponSystemProjectP2.Beans.UserType;
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

/**
 * Service class implementing all methods related to log-in token usage. Using JSON Web Token (JWT) standard and HS256 encryption algorithm.
 */
@Service
public class JWTutil {
    private final String signatureAlgorithm = SignatureAlgorithm.HS256.getJcaName();
    private final String secretKey = "7235753878214125442A472D4B6150645367566B597033733676397924423F45";
    private final Key decodedSecretKey = new SecretKeySpec(Base64.getDecoder().decode(this.secretKey), this.signatureAlgorithm);

    /**
     * Method for generating a JSON Web Token (JWT) on login.
     *
     * @param loginDetails Login details as retrieved by login function
     * @return JSON Web Token with user details: UserType, Id and Email (as Subject)
     */
    public String generateToken(LoginDetails loginDetails) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("UserType", loginDetails.getUserType());
        claims.put("Id", loginDetails.getId());
        return createToken(claims, loginDetails.getEmail());
    }

    /**
     * Method for generating a JSON Web Token (JWT) on method use (using existing token to create a new one) to extend JWT expiration.
     *
     * @param token Valid JSON Web Token
     * @return new JSON Web Token with user details: UserType, Id and Email (as Subject) from old token
     */
    public String generateToken(String token) {
        Map<String, Object> newClaims = new HashMap<>();
        Claims oldClaims = extractAllClaims(token.replace("Bearer ", ""));
        newClaims.put("UserType", oldClaims.get("UserType"));
        newClaims.put("Id", oldClaims.get("Id"));
        return createToken(newClaims, oldClaims.getSubject());
    }

    /**
     * Internal method to help generateToken methods generate a valid token.
     *
     * @param claims Map containing UserType and Id from LoginDetails
     * @param email  Email passed from LoginDetails or extracted from old token Subject
     * @return Valid, signed and encrypted JSON Web Token (JWT)
     */
    private String createToken(Map<String, Object> claims, String email) {
        Instant now = Instant.now();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(email)
                .setIssuedAt(Date.from(now))
                .setExpiration(Date.from(now.plus(10, ChronoUnit.MINUTES)))
                .signWith(this.decodedSecretKey)
                .compact();
    }

    /**
     * Method for extracting JSON Web Token claims.
     *
     * @param token Valid JSON Web Token
     * @return Parsed, decrypted claims. Including: Id, UserType, sub (Subject -> email), iat (Issued At -> start time) and exp (Expiration -> token invalidity time)
     * @throws ExpiredJwtException   Thrown if token is expired
     * @throws SignatureException    Thrown if token isn't signed/signed with incorrect key
     * @throws MalformedJwtException Thrown if token isn't built in standard form
     */
    public Claims extractAllClaims(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException {
        JwtParser jwtParser = Jwts.parserBuilder()
                .setSigningKey(this.decodedSecretKey)
                .build();
        return jwtParser.parseClaimsJws(token.replace("Bearer ", "")).getBody();
    }

    /**
     * Method for retrieving user ID from JSON Web Token claims.
     *
     * @param token Valid JSON Web Token
     * @return User ID number from JSON Web Token
     * @throws ExpiredJwtException   Thrown if token is expired
     * @throws SignatureException    Thrown if token isn't signed/signed with incorrect key
     * @throws MalformedJwtException Thrown if token isn't built in standard form
     */
    public int getIdFromToken(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException {
        final Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        return (int) claims.get("Id");
    }

    /**
     * Method for retrieving user Type from JSON Web Token claims.
     *
     * @param token Valid JSON Web Token
     * @return UserType from JSON Web Token
     * @throws ExpiredJwtException   Thrown if token is expired
     * @throws SignatureException    Thrown if token isn't signed/signed with incorrect key
     * @throws MalformedJwtException Thrown if token isn't built in standard form
     */
    public UserType getUserTypeFromToken(String token) throws ExpiredJwtException, SignatureException, MalformedJwtException {
        final Claims claims = extractAllClaims(token.replace("Bearer ", ""));
        return UserType.valueOf((String) claims.get("UserType"));
    }
}
