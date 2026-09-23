package com.facs.system_auth_login.regression;

import com.facs.system_auth_login.config.SecurityConfig;
import com.facs.system_auth_login.controller.AuthController;
import com.facs.system_auth_login.service.AuthService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(AuthController.class)
@Import(SecurityConfig.class)
class AuthRegressionTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Test
    void regressaoLoginValido() throws Exception {

        when(authService.autenticar(anyString(), anyString()))
                .thenReturn(true);

        mockMvc.perform(
                post("/auth/login")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "usuario@email.com",
                                    "senha": "123456"
                                }
                                """)
        ).andExpect(status().isOk());
    }

    @Test
    void regressaoSenhaIncorreta() throws Exception {

        when(authService.autenticar(anyString(), anyString()))
                .thenReturn(false);

        mockMvc.perform(
                post("/auth/login")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "usuario@email.com",
                                    "senha": "senhaerrada"
                                }
                                """)
        ).andExpect(status().isUnauthorized());
    }

    @Test
    void regressaoEmailInvalido() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "email-invalido",
                                    "senha": "123456"
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void regressaoEmailAusente() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "senha": "123456"
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }

    @Test
    void regressaoSenhaAusente() throws Exception {

        mockMvc.perform(
                post("/auth/login")
                        .with(csrf())
                        .contentType(APPLICATION_JSON)
                        .content("""
                                {
                                    "email": "usuario@email.com"
                                }
                                """)
        ).andExpect(status().isBadRequest());
    }
}
