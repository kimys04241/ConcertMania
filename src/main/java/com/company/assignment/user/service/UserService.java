package com.company.assignment.user.service;

import com.company.assignment.common.api.ApiStatus;
import com.company.assignment.common.exception.AlreadyExistUserException;
import com.company.assignment.common.exception.LoginInfoNotMatchedException;
import com.company.assignment.common.provider.JwtProvider;
import com.company.assignment.common.util.PasswordUtil;
import com.company.assignment.user.domian.entity.User;
import com.company.assignment.user.domian.request.LoginRequest;
import com.company.assignment.user.domian.request.SignupRequest;
import com.company.assignment.user.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    private final JwtProvider jwtProvider;


    @Transactional
    public void signup(SignupRequest signupRequest) {

        if (userRepository.existsByUserId(signupRequest.getUserId())) {
            throw new AlreadyExistUserException(ApiStatus.ALREADY_EXIST_USER_EXCEPTION_CODE,
                    ApiStatus.ALREADY_EXIST_USER_EXCEPTION_MESSAGE);
        }

        User user = signupRequest.toEntity();
        userRepository.save(user);
    }


    public String login(LoginRequest loginRequest) {

        User user = userRepository.findByUserId(loginRequest.getUserId()).orElseThrow(
                () -> new LoginInfoNotMatchedException(ApiStatus.LOGIN_INFO_NOT_MATCHED_EXCEPTION_CODE,
                        ApiStatus.LOGIN_INFO_NOT_MATCHED_EXCEPTION_MESSAGE)
        );

        if (!PasswordUtil.matchPassword(loginRequest.getPassword(), user.getPassword())) {
            throw new LoginInfoNotMatchedException(ApiStatus.LOGIN_INFO_NOT_MATCHED_EXCEPTION_CODE,
                    ApiStatus.LOGIN_INFO_NOT_MATCHED_EXCEPTION_MESSAGE);
        }

        return jwtProvider.generateToken(String.valueOf(user.getId()));
    }
}
