package com.company.assignment.common.domian.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Schema(description = "공통 응답 객체")
public class AssignmentResponse<T> {

    @Schema(description = "응답 코드", example = "200")
    private String code;
    @Schema(description = "응답 메시지", example = "Success")
    private String message;
    @Schema(description = "응답 데이터", example = "{\"accessToken\": \"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzQxMTgxNDY4LCJleHAiOjE3NDExODUwNjh9.LeE561ardIrF72XFUy8VnekferdQ91Er63aBuiCeFJA\"}")
    private T data;

    public static <T> AssignmentResponse<T> success(T data) {
        return new AssignmentResponse<>("200", "Success", data);
    }

    public static <T> AssignmentResponse<T> error(String code, String message) {
        return new AssignmentResponse<>(code, message, null);
    }
}