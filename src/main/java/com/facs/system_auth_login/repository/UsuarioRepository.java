package com.facs.system_auth_login.repository;

import com.facs.system_auth_login.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity, UUID> {
    
}
