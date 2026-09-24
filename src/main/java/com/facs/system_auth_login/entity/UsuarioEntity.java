package com.facs.system_auth_login.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Validated
@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private Long id;

    @CPF
    @Column
    private String cpf;

    @Max(value = 45)
    @NotBlank(message = "Nome não pode ser vazio!")
    @Column
    private String nome;

    @NotBlank(message = "Email não pode ser vazio!")
    @Email(message = "Email inválido!")
    @Column
    private String email;

    @Pattern(regexp = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).*$",
    message = "A senha deve conter pelo menos uma letra minúscula, uma maiúscula, um número e um caractere especial.")
    @Column
    private String senha;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column
    private LocalDate dataNascimento;

    @Column
    private LocalDateTime dataHoraCadastro;
}
