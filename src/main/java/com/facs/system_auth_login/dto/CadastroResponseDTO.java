package com.facs.system_auth_login.dto;

import java.time.LocalDate;

public record UsuarioResponseDTO(
    String nome,

    String email,

    LocalDate dataNascimento
) {}