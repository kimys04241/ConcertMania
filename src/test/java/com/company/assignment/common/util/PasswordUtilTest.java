package com.company.assignment.common.util;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PasswordUtilTest {

    @Test
    @DisplayName("올바른 비밀번호 검증")
    void testHashPassword() {
        // given
        String rawPassword = "testPassword";

        // when
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);

        // then
        assertThat(PasswordUtil.matchPassword(rawPassword, hashedPassword)).isTrue();
    }

    @Test
    @DisplayName("잘못된 비밀번호 테스트")
    void testWrongHashPassword() {
        // given
        String rawPassword = "testPassword";
        String wrongPassword = "wrongPassword";

        // when
        String hashedPassword = PasswordUtil.hashPassword(rawPassword);

        // then
        assertThat(PasswordUtil.matchPassword(wrongPassword, hashedPassword)).isFalse();
    }

    @Test
    @DisplayName("같은 비밀번호라도 해시값이 다른 테스트 Salt")
    void testSalt() {
        // given
        String rawPassword = "testPassword";

        // when
        String hashedPassword1 = PasswordUtil.hashPassword(rawPassword);
        String hashedPassword2 = PasswordUtil.hashPassword(rawPassword);

        // then
        assertThat(hashedPassword1).isNotEqualTo(hashedPassword2);
    }
}