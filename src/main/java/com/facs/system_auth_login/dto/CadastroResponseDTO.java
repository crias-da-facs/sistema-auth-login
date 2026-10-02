package com.facs.system_auth_login.dto;

import java.time.LocalDate;

public record CadastroResponseDTO(
    String nome,

    String email,

    LocalDate dataNascimento
) {}