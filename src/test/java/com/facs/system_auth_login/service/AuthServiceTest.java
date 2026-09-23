package com.facs.system_auth_login.service;

import com.facs.system_auth_login.model.Usuario;
import com.facs.system_auth_login.repository.UsuarioRepository;
import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Test
    void deveAutenticarUsuarioComCredenciaisValidas() {
        UsuarioRepository repository = mock(UsuarioRepository.class);

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
                "123456"
        );

        assertTrue(resultado);
    }

    @Test
    void naoDeveAutenticarUsuarioComSenhaIncorreta() {
        UsuarioRepository repository = mock(UsuarioRepository.class);

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

    @Test
    void naoDeveAutenticarUsuarioInexistente() {
        UsuarioRepository repository = mock(UsuarioRepository.class);

        when(repository.findByEmail("naoexiste@email.com"))
                .thenReturn(Optional.empty());

        AuthService authService = new AuthService(
                repository,
                passwordEncoder
        );

        boolean resultado = authService.autenticar(
                "naoexiste@email.com",
                "123456"
        );

        assertFalse(resultado);
    }
}
