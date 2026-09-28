package com.facs.system_auth_login.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.Pattern;

public record UsuarioResponse (
    
    String cpf,

    String nome,

    String email,

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
    message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial.")
    String senha,

    LocalDate dataNascimento
) {}