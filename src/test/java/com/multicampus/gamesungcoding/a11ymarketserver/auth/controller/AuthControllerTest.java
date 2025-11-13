package com.multicampus.gamesungcoding.a11ymarketserver.auth.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.multicampus.gamesungcoding.a11ymarketserver.auth.dto.JoinRequestDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.auth.dto.LoginDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.auth.dto.UserRespDTO;
import com.multicampus.gamesungcoding.a11ymarketserver.auth.service.AuthService;
import com.multicampus.gamesungcoding.a11ymarketserver.config.SecurityConfig;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Import(SecurityConfig.class)
@WebMvcTest(AuthController.class)
class AuthControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private AuthService authService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testLogin() throws Exception {
        var mockEmail = "user1@example.com";
        var mockName = "user1";
        var mockPassword = "1234";
        var mockUuid = UUID.randomUUID();

        var mockReqDto = LoginDTO.builder()
                .email(mockEmail)
                .password(mockPassword)
                .build();

        var mockResp = UserRespDTO.builder()
                .userId(mockUuid)
                .userEmail(mockEmail)
                .userName(mockName)
                .build();

        BDDMockito.given(authService.login(any(LoginDTO.class)))
                .willReturn(mockResp);

        mockMvc.perform(post("/api/v1/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockReqDto))
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.userId").value(mockUuid.toString()))
                .andExpect(jsonPath("$.userEmail").value(mockEmail))
                .andExpect(jsonPath("$.userName").value(mockName));
    }

    @Test
    void testLogout() throws Exception {
        mockMvc.perform(post("/api/v1/auth/logout"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").value("로그아웃 성공"));
    }

    @Test
    void testJoin() throws Exception {
        var mockName = "user1";

        var mockReqDto = JoinRequestDTO.builder()
                .email("user1@example.com")
                .password("Password123!")
                .nickname(mockName)
                .username(mockName)
                .phone("01012345678")
                .build();

        BDDMockito.given(authService.join(any(JoinRequestDTO.class)))
                .willReturn(UserRespDTO.builder()
                        .userId(UUID.randomUUID())
                        .userEmail("user1@example.com")
                        .userName(mockName)
                        .build());

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockReqDto))
                )
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.msg").value("회원가입 성공"));
    }

    @Test
    void testJoin_EmailConflict() throws Exception {
        var mockName = "user1";

        var mockReqDto = JoinRequestDTO.builder()
                .email("user1@example.com")
                .password("Password123!")
                .nickname(mockName)
                .username(mockName)
                .phone("01012345678")
                .build();

        BDDMockito.given(authService.join(any(JoinRequestDTO.class)))
                .willReturn(null);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockReqDto))
                )
                .andExpect(status().isConflict())
                .andExpect(jsonPath("$.message").value("이미 존재하는 이메일입니다."));
    }

    @Test
    void testJoin_InvalidPassword() throws Exception {
        var mockName = "user1";

        var mockReqDto = JoinRequestDTO.builder()
                .email("user1@example.com")
                .password("1234")
                .nickname(mockName)
                .username(mockName)
                .phone("01012345678")
                .build();

        BDDMockito.given(authService.join(any(JoinRequestDTO.class)))
                .willReturn(null);

        mockMvc.perform(post("/api/v1/auth/join")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(mockReqDto))
                )
                .andExpect(status().isBadRequest());
    }
}
