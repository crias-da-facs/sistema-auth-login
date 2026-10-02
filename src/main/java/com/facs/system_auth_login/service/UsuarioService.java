package com.facs.system_auth_login.service;

import com.facs.system_auth_login.entity.UsuarioEntity;
import com.facs.system_auth_login.repository.UsuarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    UsuarioService (UsuarioRepository usuarioRepository){
        this.usuarioRepository = usuarioRepository;
    }

    public List<UsuarioEntity> buscarUsuarios(){
        return usuarioRepository.findAll();
    }
}
