package com.gyl.api_gestionComercio.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${api.security.secret}")
    private String claveSecreta;

    public String getToken(UserDetails user) {
        Algorithm algorithm = Algorithm.HMAC256(claveSecreta);
        return JWT.create()
                .withSubject(user.getUsername())
                .withExpiresAt(crearFechaDeExpiracion())
                .sign(algorithm);
    }

    private Instant crearFechaDeExpiracion() {
        return LocalDateTime.now().plusHours(15).toInstant(ZoneOffset.UTC);
    }

    public String getUsernameFromToken(String token) {
        try {
            Algorithm algorithm = Algorithm.HMAC256(claveSecreta);
            JWTVerifier verifier = JWT.require(algorithm).build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject();
        } catch (JWTVerificationException exception) {
            throw new RuntimeException("Token invalido o expirado");
        }
    }

    public boolean isTokenValid(String token, UserDetails userDetails) {
        String username = getUsernameFromToken(token);
        return (username != null && username.equals(userDetails.getUsername()));
    }
}
