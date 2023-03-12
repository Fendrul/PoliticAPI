package be.techni.PoliticAPI.jwt;

import be.techni.PoliticAPI.models.entities.User;
import be.techni.PoliticAPI.services.UserDetailsServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtProvider {

    @Value("${jwt.expirationTime}")
    private int expirationTime;
    @Value("${jwt.secret}")
    private String secret;

    public String createToken(User user) {
        Claims claims = Jwts.claims().setSubject(user.getUsername());
        claims.put("roles", UserDetailsServiceImpl.getAuthorities(user));
        claims.put("id", user.getUser_id());

        Date now = new Date();
        Date expirationDate = new Date(now.getTime() + expirationTime);

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expirationDate)
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS512, secret)
                .compact();
    }

    public boolean validateToken(String token) {
        final Date dateTokenCreated = extractExpiration(token);

        return (!isTokenExpired(dateTokenCreated));
    }

    public String extractUsername(String jwt) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(jwt).getBody().getSubject();
    }

    private Date extractExpiration(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getExpiration();
    }

    private boolean isTokenExpired(Date dateTokenCreated) {
        return dateTokenCreated.before(new Date());
    }
}
