package com.facs.system_auth_login.controller;

import com.facs.system_auth_login.config.SecurityConfig;
import com.facs.system_auth_login.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@org.springframework.context.annotation.Import(SecurityConfig.class)
class AuthControllerEmailEspacosTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Test
    void deveRetornar400QuandoEmailPossuirEspacos() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .with(csrf())
                        .contentType("application/json")
                        .content("""
                                {
                                    "email": "usuario @email.com",
                                    "senha": "123456"
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }
}
