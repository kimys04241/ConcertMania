package com.company.assignment.user.controller;

import com.company.assignment.common.domian.response.AssignmentResponse;
import com.company.assignment.user.domian.request.LoginRequest;
import com.company.assignment.user.domian.request.SignupRequest;
import com.company.assignment.user.domian.response.LoginResponse;
import com.company.assignment.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "회원 가입",
            description = "사용자가 회원 가입을 요청 API"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "회원 가입 성공",
                    content = @Content(
                            schema = @Schema(implementation = AssignmentResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "회원 가입 성공",
                                    value = """
                                            {
                                              "code": "200",
                                              "message": "Success",
                                              "data": null
                                            }
                                            """
                            )
                    )),
            @ApiResponse(responseCode = "400001", description = "필수 필드 에러 입니다.",
                    content = @Content(
                            schema = @Schema(implementation = AssignmentResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "필수 필드 에러 입니다.",
                                    value = """
                                            {
                                              "code": "400001",
                                              "message": "필수 필드 에러 입니다.",
                                              "data": null
                                            }
                                            """
                            )
                    )),
            @ApiResponse(responseCode = "400003", description = "이미 존재하는 유저 입니다.",
                    content = @Content(
                            schema = @Schema(implementation = AssignmentResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "이미 존재하는 유저 입니다.",
                                    value = """
                                            {
                                              "code": "400003",
                                              "message": "이미 존재하는 유저 입니다.",
                                              "data": null
                                            }
                                            """
                            )
                    ))

    })
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@Valid @RequestBody SignupRequest signupRequest) {
        userService.signup(signupRequest);
        return ResponseEntity.ok(AssignmentResponse.success(null));
    }

    @Operation(
            summary = "로그인",
            description = "로그인 API"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(
                            schema = @Schema(implementation = AssignmentResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "로그인 성공",
                                    value = """
                                {
                                  "code": "200",
                                  "message": "Success",
                                  "data": {
                                            "accessToken": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiaWF0IjoxNzQxMTgxNDY4LCJleHAiOjE3NDExODUwNjh9.LeE561ardIrF72XFUy8VnekferdQ91Er63aBuiCeFJA"
                                          }
                                }
                                """
                            )
                    )),
            @ApiResponse(responseCode = "400001", description = "필수 필드 에러 입니다.",
                    content = @Content(
                            schema = @Schema(implementation = AssignmentResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "필수 필드 에러 입니다.",
                                    value = """
                                {
                                  "code": "400001",
                                  "message": "필수 필드 에러 입니다.",
                                  "data": null
                                }
                                """
                            )
                    )),
            @ApiResponse(responseCode = "400004", description = "로그인정보 오류",
                    content = @Content(
                            schema = @Schema(implementation = AssignmentResponse.class),
                            examples = @io.swagger.v3.oas.annotations.media.ExampleObject(
                                    name = "로그인정보 오류",
                                    value = """
                                {
                                  "code": "400004",
                                  "message": "허용되지 않는 유저 입니다.",
                                  "data": null
                                }
                                """
                            )
                    ))

    })
    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginRequest loginRequest) {

        String accessToken = userService.login(loginRequest);
        return ResponseEntity.ok().body(
                AssignmentResponse.success(
                        LoginResponse.builder().accessToken(accessToken).build()
                ));
    }
}