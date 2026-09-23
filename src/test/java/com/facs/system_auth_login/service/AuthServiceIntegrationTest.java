package com.facs.system_auth_login.service;

import com.facs.system_auth_login.model.Usuario;
import com.facs.system_auth_login.repository.UsuarioRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
class AuthServiceIntegrationTest {

    @Autowired
    private AuthService authService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @BeforeEach
    void limparBanco() {
        usuarioRepository.deleteAll();
    }

    @Test
    void deveAutenticarUsuarioRealmentePersistidoNoBanco() {

        Usuario usuario = new Usuario(
                "integracao@email.com",
                passwordEncoder.encode("123456")
        );

        usuarioRepository.save(usuario);

        boolean resultado = authService.autenticar(
                "integracao@email.com",
                "123456"
        );

        assertTrue(resultado);
    }

    @Test
    void naoDeveAutenticarUsuarioComSenhaIncorretaNoBanco() {

        Usuario usuario = new Usuario(
                "integracao2@email.com",
                passwordEncoder.encode("123456")
        );

        usuarioRepository.save(usuario);

        boolean resultado = authService.autenticar(
                "integracao2@email.com",
                "senhaerrada"
        );

        assertFalse(resultado);
    }
}
