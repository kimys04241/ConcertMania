package com.company.assignment.user.service;

import com.company.assignment.common.api.ApiStatus;
import com.company.assignment.common.exception.AlreadyExistUserException;
import com.company.assignment.common.exception.LoginInfoNotMatchedException;
import com.company.assignment.common.provider.JwtProvider;
import com.company.assignment.common.util.CryptoUtil;
import com.company.assignment.common.util.PasswordUtil;
import com.company.assignment.user.domian.entity.User;
import com.company.assignment.user.domian.request.LoginRequest;
import com.company.assignment.user.domian.request.SignupRequest;
import com.company.assignment.user.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private JwtProvider jwtProvider;

    @InjectMocks
    private UserService userService;

    private SignupRequest signupRequest;

    @BeforeEach
    void setUp() {
        signupRequest = SignupRequest.builder()
                .userId("testUserId")
                .password("testPassword")
                .name("동탁")
                .build();
    }


    @Test
    @DisplayName("정상 가입")
    void testSignup() {
        // given
        String ssnHash = CryptoUtil.sha256("921108-1582816"); // 동탁
        when(userRepository.existsByUserId(ssnHash)).thenReturn(false);
        when(userRepository.save(any())).thenReturn(new User());

        // when
        assertDoesNotThrow(() -> userService.signup(signupRequest));

        // then
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    @DisplayName("정상 로그인")
    public void testLoginSuccess() {
        // given
        Long id = 1L;
        String userId = "user";
        String rawPassword = "password";

        User user = User.builder()
                .id(id)
                .userId(userId)
                .password(PasswordUtil.hashPassword(rawPassword))
                .build();

        LoginRequest loginRequest = LoginRequest.builder().userId(userId).password(rawPassword).build();

        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));
        when(jwtProvider.generateToken(String.valueOf(id))).thenReturn("jwtToken");

        // when
        String token = userService.login(loginRequest);

        // then
        assertEquals("jwtToken", token);
    }

    @Test
    @DisplayName("로그인 실패 유저 없음")
    public void testLoginUserNotFound() {
        // given
        String userId = "user1";
        String rawPassword = "password1";
        LoginRequest loginRequest = new LoginRequest(userId, rawPassword);

        when(userRepository.findByUserId(userId)).thenReturn(Optional.empty());

        // when then
        assertThrows(LoginInfoNotMatchedException.class, () -> userService.login(loginRequest));
    }

    @Test
    @DisplayName("로그인 실패 비밀번호 불일치")
    public void testLoginWrongPassword() {
        // given
        String userId = "user1";
        String rawPassword = "password1";
        // 실제 저장된 비밀번호는 다른 값으로 가정합니다.
        User user = User.builder()
                .userId(userId)
                .password(PasswordUtil.hashPassword("differentPassword"))
                .build();

        LoginRequest loginRequest = new LoginRequest(userId, rawPassword);

        when(userRepository.findByUserId(userId)).thenReturn(Optional.of(user));

        // when & then
        assertThrows(LoginInfoNotMatchedException.class, () -> userService.login(loginRequest));
    }
}