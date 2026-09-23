package com.facs.system_auth_login.repository;

import com.facs.system_auth_login.model.Usuario;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class UsuarioRepositoryTest {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Test
    void deveSalvarEBuscarUsuarioPorEmailComSenhaCriptografada() {

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        String senhaOriginal = "123456";
        String senhaCriptografada =
                passwordEncoder.encode(senhaOriginal);

        Usuario usuario = new Usuario(
                "teste@email.com",
                senhaCriptografada
        );

        usuarioRepository.save(usuario);

        Optional<Usuario> resultado =
                usuarioRepository.findByEmail("teste@email.com");

        assertTrue(resultado.isPresent());

        assertTrue(
                passwordEncoder.matches(
                        senhaOriginal,
                        resultado.get().getSenha()
                )
        );
    }
}
