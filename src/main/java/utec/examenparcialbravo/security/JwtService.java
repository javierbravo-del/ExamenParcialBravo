package utec.examenparcialbravo.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class JwtService {

    @Value("${jwt.secret}") private String jwtSecret;
    @Value ("${jwt.expiration}") private Long jwtExpiration;
    private SecretKey key(){
        return Keys.hmacShaKeyFor(jwtSecret.getBytes(StandardCharsets.UTF_8));
    }
    public String generateToken(String username, String role){
        return Jwts.builder()
                .subject(username)
                .claim("role", role)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(key())
                .compact();
    }
    public Claims extractClaims(String token){
        return Jwts.parser().verifyWith(key()).build()
                .parseSignedClaims(token).getPayload();
    }
    public String extractUsername(String token){
        return extractClaims(token).getSubject();
    }
    public boolean isTokenValid(String token){
        try {extractClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e){
            return false;
        }
    }
}
