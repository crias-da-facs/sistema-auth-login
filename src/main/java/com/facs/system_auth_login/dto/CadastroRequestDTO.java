package com.facs.system_auth_login.dto;

import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public record CadastroRequestDTO(
        @NotBlank(message = "CPF não pode ser vazio!")
        @CPF
        String cpf,

        @NotBlank(message = "Nome não pode ser vazio!")
        @Size(max = 45, message = "O nome não pode ter mais de 45 caracteres!")
        String nome,

        @NotBlank(message = "Email não pode ser vazio!")
        @Email(message = "Email inválido!")
        String email,

        @NotBlank(message = "Senha não pode ser vazia!")
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
                message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial.")
        String senha,

        @NotNull(message = "Data de nascimento não pode estar vazia!")
        @Past(message = "Use uma data de nascimento que tenha existido!")
        @DateTimeFormat(pattern = "yyyy-MM-dd")
        LocalDate dataNascimento
) {}