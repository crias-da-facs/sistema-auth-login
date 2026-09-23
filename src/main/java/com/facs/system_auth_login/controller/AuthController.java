package com.facs.system_auth_login.controller;

import com.facs.system_auth_login.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginRequest request) {

        if (request.email() == null || request.email().isBlank()
                || request.senha() == null || request.senha().isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        if (!request.email().matches("^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$")) {
            return ResponseEntity.badRequest().build();
        }

        boolean autenticado = authService.autenticar(
                request.email(),
                request.senha()
        );

        if (autenticado) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.status(401).build();
    }

    public record LoginRequest(
            String email,
            String senha
    ) {
    }
}
