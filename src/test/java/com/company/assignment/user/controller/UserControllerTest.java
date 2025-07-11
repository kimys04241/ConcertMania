package com.company.assignment.user.controller;

import com.company.assignment.common.provider.JwtProvider;
import com.company.assignment.user.domian.request.LoginRequest;
import com.company.assignment.user.service.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureMockMvc(addFilters = false)
@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UserService userService;

    @MockitoBean
    private JwtProvider jwtProvider;

    @Test
    @DisplayName("회원가입 성공")
    void testSignup() throws Exception {

        // given
        String requestBody = """
                {
                    "userId": "test",
                    "password": "password",
                    "name": "동탁"
                }
                """;
        doNothing().when(userService).signup(any());
        // when
        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.message").value("Success"));

    }

    @Test
    @DisplayName("회원가입 실패 필수값 누락")
    void testRequiredFiledException() throws Exception {
        // given
        String requestBody = """
                {
                    "userId" : "",
                    "password" : "",
                    "name" : "동탁"
                }
                """;

        // when  then
        mockMvc.perform(post("/user/signup")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isBadRequest()) // 400 Bad Request 예상
                .andExpect(jsonPath("$.code").value("400001"))
                .andExpect(jsonPath("$.message").value("필수 필드 에러 입니다."));
    }

    @Test
    @DisplayName("로그인 성공 시 JWT 토큰을 포함한 성공 응답 반환")
    public void testLoginSuccess() throws Exception {
        // given
        String jwtToken = "jwtToken123";

        String requestBody = """
                {
                    "userId" : "userid",
                    "password" : "password"
                }
                """;

        when(userService.login(any(LoginRequest.class))).thenReturn(jwtToken);
        when(jwtProvider.generateToken(any())).thenReturn(jwtToken);


        // when & then
        mockMvc.perform(post("/user/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("200"))
                .andExpect(jsonPath("$.data.accessToken").value(jwtToken));
    }
}