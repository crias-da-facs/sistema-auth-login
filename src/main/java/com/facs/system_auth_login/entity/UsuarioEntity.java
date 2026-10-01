package com.facs.system_auth_login.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.br.CPF;
import org.jspecify.annotations.Nullable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.annotation.Validated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Validated
@Data
@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioEntity implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @CPF
    @Column(name = "cpf", unique = true)
    private String cpf;

    @Size(max = 45, message = "O nome não pode ter mais de 45 caracteres!")
    @NotBlank(message = "Nome não pode ser vazio!")
    @Column(name = "nome", length = 45)
    private String nome;

    @NotBlank(message = "Email não pode ser vazio!")
    @Email(message = "Email inválido!")
    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "senha")
    private String senha;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "dataNascimento")
    private LocalDate dataNascimento;

    @Column(name = "dataHoraCadastro")
    private LocalDateTime dataHoraCadastro;

    @Column(name = "role")
    private UsuarioRole role;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(this.role == UsuarioRole.ADMIN) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"), new SimpleGrantedAuthority("ROLE_USUARIO"));
        }
        else{
            return List.of(new SimpleGrantedAuthority("ROLE_USUARIO"));
        }
    }

    @Override
    public @Nullable String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }
}
