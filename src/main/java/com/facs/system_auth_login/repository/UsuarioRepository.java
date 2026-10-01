package com.facs.system_auth_login.repository;

import com.facs.system_auth_login.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    UserDetails findUserByEmail(String email);
}
