package com.facs.system_auth_login.controller;

import com.facs.system_auth_login.dto.CadastroRequestDTO;
import com.facs.system_auth_login.dto.CadastroResponseDTO;
import com.facs.system_auth_login.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthService authService;

    AuthController(AuthService authService){
        this.authService = authService;
    }

    @PostMapping("/cadastro")
    public ResponseEntity<CadastroResponseDTO> cadastrar(@RequestBody @Valid CadastroRequestDTO cadastroRequestDTO){
        CadastroResponseDTO cadastroResponseDTO = authService.cadastrarNovoUsuario(cadastroRequestDTO);

        return ResponseEntity.status(HttpStatus.CREATED).body(cadastroResponseDTO);

    }
}
