package com.medi.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.medi.api.domain.user.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {
    @Value("${api.security.token.secret}")
    private String secret;
    public String gerarToken(User user){
        try{
            var algoritmo = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer("API Voll")
                    .withSubject(user.getEmail())
                    .withExpiresAt(dataExpiracao())
                    .withClaim("nome", user.getNome())
                    .sign(algoritmo);
        }catch(JWTCreationException exception){
            throw new RuntimeException("erro ao criar token", exception);
        }
    }

    public Instant dataExpiracao(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }

    public String recuperarToken(String token){
        if(token == null) return null;
        Algorithm algoritmo = Algorithm.HMAC256(secret);
        return JWT.require(algoritmo)
                .withIssuer("API Voll")
                .build()
                .verify(token)
                .getSubject();
    }

}
