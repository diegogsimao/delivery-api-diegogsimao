package com.deliverytech.delivery.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;
import com.deliverytech.delivery.entity.Users;

@Service
public class TokenService {

    @Value("${api.security.token.secret}")
    private String secret;

    public String generateToken(Users user) {
        // Lógica para gerar o token JWT
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            String token = com.auth0.jwt.JWT.create()
                    .withIssuer("DeliveryTech")
                    .withSubject(user.getEmail())
                    .withExpiresAt(generationExpirationDate())
                    .sign(algorithm);

            return token;

        } catch (JWTCreationException exception) {
            throw new RuntimeException("Erro ao gerar token JWT", exception);
        }
    }

    public String validateToken(String token) {
        try {

            Algorithm algorithm = Algorithm.HMAC256(secret);
            JWTVerifier verifier = JWT.require(algorithm)
                    .withIssuer("DeliveryTech")
                    .build();

            DecodedJWT jwt = verifier.verify(token);
            return jwt.getSubject();

        } catch (JWTVerificationException exception) {
            return "";
        }
    }

    private Date generationExpirationDate() {
        Instant instant = LocalDateTime.now().plusHours(2).toInstant(java.time.ZoneOffset.of("-03:00"));
        Date date = Date.from(instant);
        return date;
    }
}
