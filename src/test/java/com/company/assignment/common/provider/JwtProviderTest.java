package com.company.assignment.common.provider;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;

class JwtProviderTest {

    private JwtProvider jwtProvider;

    @BeforeEach
    void setUp() {
        jwtProvider = new JwtProvider();
    }

    @Test
    @DisplayName("토큰 생성 및 검증")
    void testGenerateTokenAndValidation() {
        // given
        String userId = "testUser";

        // when
        String token = jwtProvider.generateToken(userId);
        boolean isValid = jwtProvider.validateToken(token);

        // then
        assertNotNull(token);
        assertTrue(isValid);
    }

    @Test
    @DisplayName("토큰에서 유저아이디 추출")
    void testGetUserIdFromToken() {
        // given
        String userId = "testUser";
        String token = jwtProvider.generateToken(userId);

        // when
        String extractedUserId = jwtProvider.getUserIdFromToken(token);

        // then
        assertEquals(userId, extractedUserId);
    }

    @Test
    @DisplayName("유효하지 않은 토큰 검증")
    void testInvalidToken() {
        // given
        String invalidToken = "invalidTokenString";

        // when
        boolean isValid = jwtProvider.validateToken(invalidToken);

        // then
        assertFalse(isValid);
    }

    @Test
    @DisplayName("만료된 토큰 검증")
    void testExpiredToken() throws InterruptedException {

        // given
        String userId = "testUser";
        jwtProvider.setExpirationTime(1000L);
        String token = jwtProvider.generateToken(userId);
        TimeUnit.MILLISECONDS.sleep(1500);

        // when
        boolean isValid = jwtProvider.validateToken(token);

        // then
        assertFalse(isValid);
    }
}