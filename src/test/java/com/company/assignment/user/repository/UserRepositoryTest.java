package com.company.assignment.user.repository;

import com.company.assignment.user.domian.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("existsBySsnHash 메서드 테스트")
    public void testExistsBySsnHash() {
        // given
        String ssnHash = "hashed-ssn-123";
        User user = User.builder()
                .userId("userId")
                .password("password")
                .name("name")
                .ssn("ssn")
                .ssnHash(ssnHash)
                .build();
        userRepository.save(user);

        // when
        boolean exists = userRepository.existsByUserId(ssnHash);
        boolean notExists = userRepository.existsByUserId("12388328");

        // then
        assertThat(exists).isTrue();
        assertThat(notExists).isFalse();
    }

    @Test
    @DisplayName("유저 아이디 기반으로 조회")
    public void testFindByUserId() {
        // given
        String userId = "user2";
        String password = "password2";
        User user = User.builder()
                .userId(userId)
                .password(password)
                .name("name")
                .ssn("ssn")
                .ssnHash("ssnHash")
                .build();
        userRepository.save(user);

        // when
        Optional<User> foundUser = userRepository.findByUserId(userId);
        Optional<User> notFoundUser = userRepository.findByUserId("wrongUser");

        // then
        assertThat(foundUser.isPresent()).isTrue();
        assertThat(foundUser.get().getUserId()).isEqualTo(userId);
        assertThat(notFoundUser.isPresent()).isFalse();
    }
}