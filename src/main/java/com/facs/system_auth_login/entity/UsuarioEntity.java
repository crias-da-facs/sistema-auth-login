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
import java.util.UUID;

@Validated
@Data
@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CPF
    @Column(name = "cpf")
    private String cpf;

    @Max(value = 45)
    @NotBlank(message = "Nome não pode ser vazio!")
    @Column(name = "nome")
    private String nome;

    @NotBlank(message = "Email não pode ser vazio!")
    @Email(message = "Email inválido!")
    @Column(name = "email")
    private String email;

    @Column(name = "senha")
    private String senha;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "dataHoraCadastro")
    private LocalDateTime dataHoraCadastro;
}
