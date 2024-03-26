package com.medi.api.controllers;

import com.medi.api.domain.user.User;
import com.medi.api.infra.security.JwtTokenDTO;
import com.medi.api.infra.security.TokenService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class AuthController {
    @Autowired
    private AuthenticationManager manager;
    @Autowired
    private TokenService tokenService;
    @PostMapping()
    public ResponseEntity login(@RequestBody @Valid DadosLoginDTO dados) throws Exception {
        var token = new UsernamePasswordAuthenticationToken(dados.email(),dados.senha());
        var isAutheticated = manager.authenticate(token);
        var tokenJwt = tokenService.gerarToken((User) isAutheticated.getPrincipal());
        return ResponseEntity.ok(new JwtTokenDTO(tokenJwt));
    }
}
