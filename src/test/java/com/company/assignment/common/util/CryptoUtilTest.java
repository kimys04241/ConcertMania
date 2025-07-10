package com.company.assignment.common.util;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

class CryptoUtilTest {

    private String plainText;

    @BeforeEach
    void setUp() {
        plainText = "test_text";
    }

    @Test
    @DisplayName("암복호화 테스트")
    void testEncryptDecrypt() {
        // given
        String encrypted = CryptoUtil.aesEncrypt(plainText);
        System.out.println(encrypted.length());
        // when
        String decrypted = CryptoUtil.aesDecrypt(encrypted);

        // then
        assertThat(encrypted).isNotEqualTo(plainText);
        assertThat(decrypted).isEqualTo(plainText);
    }

    @Test
    @DisplayName("sha-256 검증")
    void testSha256() {
        // given
        String text1 = "testPassword";
        String text2 = "testPassword";
        String text3 = "diffPassword";

        // when
        String hash1 = CryptoUtil.sha256(text1);
        String hash2 = CryptoUtil.sha256(text2);
        String hash3 = CryptoUtil.sha256(text3);

        // then
        assertThat(hash1).isEqualTo(hash2);
        assertThat(hash1).isNotEqualTo(hash3);
        assertThat(hash1).hasSize(64);
    }

}