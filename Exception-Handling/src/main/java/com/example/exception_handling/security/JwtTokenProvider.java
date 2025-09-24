//package com.example.exception_handling.security;
//
//import io.jsonwebtoken.Jwts;
//import io.jsonwebtoken.MalformedJwtException;
//import io.jsonwebtoken.SignatureAlgorithm;
//import io.jsonwebtoken.security.Keys;
//import org.springframework.http.HttpStatus;
//import org.springframework.security.core.Authentication;
//import org.springframework.stereotype.Component;
//import com.example.exception_handling.exception.Exception_Handeling_API;
//import java.security.Key;
//import java.util.Date;
//
//@Component
//public class JwtTokenProvider {
//    private final String SECRET_KEY = "my-name-is-akhilesh-sahani-256bit-key-123456"; // should be at least 256 bits
//    private final long EXPIRATION_TIME = 1000 * 60 * 60; // 1 hour
//    private final Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
//
//    // Generate token
//    public String generateToken(Authentication authentication) {
//        String name = authentication.getName();
//        Date now = new Date();
//        Date expiryDate = new Date(now.getTime() + EXPIRATION_TIME);
//        return Jwts.builder()
//                .setSubject(name)
//                .setIssuedAt(now)
//                .setExpiration(expiryDate)
//                .signWith(key, SignatureAlgorithm.HS256)
//                .compact();
//    }
//
//    // Get username from JWT token
//    public String getUsername(String token) {
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody()
//                .getSubject();
//    }
//
//    // Validate JWT token
//    public Boolean validateToken(String token) {
//        try {
//            Jwts.parserBuilder()
//                    .setSigningKey(key)
//                    .build()
//                    .parseClaimsJws(token);
//            return true;
//        } catch (MalformedJwtException malformedJwtException) {
//            throw new Exception_Handeling_API(HttpStatus.BAD_REQUEST, "invalid jwt token");
//        } catch (Exception e) {
//            throw new Exception_Handeling_API(HttpStatus.BAD_REQUEST, "invalid or expired jwt token");
//        }
//    }
//}