package com.company.assignment.user.domian.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LoginRequest {

    @Schema(description = "사용자 ID", example = "user123")
    @NotBlank
    private String userId;

    @Schema(description = "비밀번호", example = "password123")
    @NotBlank
    private String password;
}
