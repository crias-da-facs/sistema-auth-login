package com.facs.system_auth_login.service;

import com.facs.system_auth_login.dto.CadastroRequestDTO;
import com.facs.system_auth_login.dto.CadastroResponseDTO;
import com.facs.system_auth_login.entity.UsuarioEntity;
import com.facs.system_auth_login.entity.UsuarioRole;
import com.facs.system_auth_login.exception.CpfJaCadastradoException;
import com.facs.system_auth_login.exception.EmailJaCadastradoException;
import com.facs.system_auth_login.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class AuthService implements UserDetailsService {
    private final PasswordEncoder passwordEncoder;
    private final UsuarioRepository usuarioRepository;

    AuthService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder){
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario não encontrado com o email: " + username));
    }

    public CadastroResponseDTO cadastrarNovoUsuario(CadastroRequestDTO cadastroRequestDTO){
        if(usuarioRepository.existsByCpf(cadastroRequestDTO.cpf())){
            throw new CpfJaCadastradoException("Este cpf já está cadastrado!");
        } else if (usuarioRepository.existsByEmail(cadastroRequestDTO.email())) {
            throw new EmailJaCadastradoException("Este email já está cadastrado!");
        }

        final String senhaCriptografada = passwordEncoder.encode(cadastroRequestDTO.senha());

        UsuarioEntity novoUsuario = new UsuarioEntity();

        novoUsuario.setCpf(cadastroRequestDTO.cpf());
        novoUsuario.setEmail(cadastroRequestDTO.email());
        novoUsuario.setNome(cadastroRequestDTO.nome());
        novoUsuario.setSenha(senhaCriptografada);
        novoUsuario.setDataNascimento(cadastroRequestDTO.dataNascimento());
        novoUsuario.setRole(UsuarioRole.USUARIO);
        novoUsuario.setDataHoraCadastro(LocalDateTime.now());

        UsuarioEntity usuarioSalvo = usuarioRepository.saveAndFlush(novoUsuario);

        return new CadastroResponseDTO(usuarioSalvo.getNome(), usuarioSalvo.getEmail(), usuarioSalvo.getDataNascimento());
    }
}
