package com.company.assignment.user.domian.request;

import com.company.assignment.common.util.CryptoUtil;
import com.company.assignment.user.domian.entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SignupRequest {

    @Schema(description = "사용자 ID", example = "user123")
    @NotBlank
    private String userId;

    @Schema(description = "비밀번호", example = "password123")
    @NotBlank
    private String password;

    @Schema(description = "사용자 이름", example = "동탁")
    @NotBlank
    private String name;

    @Schema(description = "사용자 주민번호", example = "921108-1582816")
    @NotBlank
    private String regNo;


    public User toEntity() {
        return User.builder()
                .userId(this.userId)
                .password(this.password)
                .name(this.name)
                .ssn(this.regNo)
                .ssnHash(CryptoUtil.sha256(regNo))
                .build();
    }
}

