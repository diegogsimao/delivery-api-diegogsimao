package com.deliverytech.delivery.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deliverytech.delivery.DTOs.Requests.LoginRequestDTO;
import com.deliverytech.delivery.DTOs.Requests.RegisterDTO;
import com.deliverytech.delivery.DTOs.Response.LoginResponseDTO;
import com.deliverytech.delivery.entity.Users;
import com.deliverytech.delivery.repository.UserRepository;
import com.deliverytech.delivery.security.TokenService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@RestController
@RequestMapping("auth")
@Tag(name = "Autenticação", description = "APIs para gerenciamento de autenticação")
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("login")
    public ResponseEntity login(
            @RequestBody 
            @Valid 
            LoginRequestDTO loginRequest) {

        var userNamePassorword = new UsernamePasswordAuthenticationToken(
                loginRequest.email(), loginRequest.password());

        var auth = authenticationManager.authenticate(userNamePassorword);

        var token = tokenService.generateToken((Users) auth.getPrincipal());

        return ResponseEntity.ok(new LoginResponseDTO(token));
    }

    @PostMapping("register")
    public ResponseEntity<Void> register(
        @RequestBody 
        @Valid 
        RegisterDTO registerRequest) {

        if (this.userRepository.findByEmail(registerRequest.email()) != null)
            return ResponseEntity.ok().build();

        String encryptedPassword = new BCryptPasswordEncoder().encode(registerRequest.password());
        Users user = new Users(
                registerRequest.email(),
                encryptedPassword,
                Set.of(registerRequest.role()),
                registerRequest.email());

        this.userRepository.save(user);

        return ResponseEntity.ok().build();
    }
}
