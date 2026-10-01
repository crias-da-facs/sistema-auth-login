package com.facs.system_auth_login.entity;

public enum UsuarioRole {
    ADMIN("admin"),
    USUARIO("usuario");

    private String role;

    UsuarioRole(String role){
        this.role = role;
    }

    String getUsuarioRole(){
        return role;
    }
}
