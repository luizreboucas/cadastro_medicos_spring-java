package com.medi.api.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.medi.api.domain.user.User;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    public String gerarToken(User user){
        var secret = "123456";
        var algorithm = Algorithm.HMAC256(secret);
        try{
            return JWT.create()
                    .withIssuer("Api Voll")
                    .withSubject(user.getEmail())
                    .withExpiresAt(getExpireDate())
                    .sign(algorithm);
        }catch(JWTCreationException exception){
            throw new RuntimeException("erro ao criar token", exception);
        }
    }

    private Instant getExpireDate(){
        return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
    }
}
