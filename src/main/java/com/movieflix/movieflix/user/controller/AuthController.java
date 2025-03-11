package com.movieflix.movieflix.user.controller;

import com.movieflix.movieflix.config.TokenService;
import com.movieflix.movieflix.exception.UsernameOrPasswordInvalidException;
import com.movieflix.movieflix.user.entity.User;
import com.movieflix.movieflix.user.mapper.UserMapper;
import com.movieflix.movieflix.user.request.LoginRequest;
import com.movieflix.movieflix.user.request.LoginResponse;
import com.movieflix.movieflix.user.request.UserRequest;
import com.movieflix.movieflix.user.response.UserResponse;
import com.movieflix.movieflix.user.service.UserService;
import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movieflix/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;
    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;

    @PostMapping("/register")
    public ResponseEntity<UserResponse> register(@RequestBody UserRequest userRequest) {
        User savedUser = userService.saveUser(UserMapper.toUser(userRequest));
        return ResponseEntity.status(HttpStatus.CREATED).body(UserMapper.toUserResponse(savedUser));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request) {
        try {
            UsernamePasswordAuthenticationToken userAndPass = new UsernamePasswordAuthenticationToken(request.email(), request.password());
            Authentication authentication = authenticationManager.authenticate(userAndPass); // aqui ele vai chamar automaticamente o metodo loadUserByUsername da classe AuthService

            User user = (User) authentication.getPrincipal(); // pego meu usuário autenticado

            String token = tokenService.generateToken(user);

            return ResponseEntity.ok(new LoginResponse(token));
        }catch (BadCredentialsException e){
            throw new UsernameOrPasswordInvalidException("Usuário ou senha inválidos.");
        }
    }

}
