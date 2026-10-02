package com.facs.system_auth_login.controller;

import com.facs.system_auth_login.entity.UsuarioEntity;
import com.facs.system_auth_login.service.UsuarioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;


@RequestMapping("/usuario")
@RestController
public class UsuarioController {
    private final UsuarioService usuarioService;

    UsuarioController (UsuarioService usuarioService){
        this.usuarioService = usuarioService;
    }

    @GetMapping("/lista")
    public ResponseEntity<List<UsuarioEntity>> ListaUsuarios(){
        return ResponseEntity.ok(usuarioService.buscarUsuarios());
    }

}
