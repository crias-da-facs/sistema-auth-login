package com.facs.system_auth_login.dto;

import java.time.LocalDate;

public record UsuarioRequest (
    
    String cpf,

    String nome,

    String email,

    String senha,

    LocalDate dataNascimento
) {}