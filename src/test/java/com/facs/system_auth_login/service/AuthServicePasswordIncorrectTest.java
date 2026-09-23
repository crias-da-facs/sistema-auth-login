package com.facs.system_auth_login.service;

import com.facs.system_auth_login.model.Usuario;
import com.facs.system_auth_login.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class AuthServicePasswordIncorrectTest {

    @Test
    void naoDeveAutenticarQuandoSenhaCriptografadaNaoCorrespondente() {

        UsuarioRepository repository = mock(UsuarioRepository.class);

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        Usuario usuario = new Usuario(
                "usuario@email.com",
                passwordEncoder.encode("123456")
        );

        when(repository.findByEmail("usuario@email.com"))
                .thenReturn(Optional.of(usuario));

        AuthService authService = new AuthService(
                repository,
                passwordEncoder
        );

        boolean resultado = authService.autenticar(
                "usuario@email.com",
                "senhaerrada"
        );

        assertFalse(resultado);
    }
}
