package com.company.assignment.common.api;

public class ApiStatus {

    public static final String SYSTEM_EXCEPTION_CODE = "500001";
    public static final String SYSTEM_EXCEPTION_MESSAGE = "시스템 에러 입니다.";

    public static final String REQUIRED_FIELD_EXCEPTION_CODE = "400001";
    public static final String REQUIRED_FIELD_EXCEPTION_MESSAGE = "필수 필드 에러 입니다.";

    public static final String NOT_ALLOWED_USER_EXCEPTION_CODE = "400002";
    public static final String NOT_ALLOWED_USER_EXCEPTION_MESSAGE = "허용되지 않는 유저 입니다.";

    public static final String ALREADY_EXIST_USER_EXCEPTION_CODE = "400003";
    public static final String ALREADY_EXIST_USER_EXCEPTION_MESSAGE = "이미 존재하는 유저 입니다.";

    public static final String LOGIN_INFO_NOT_MATCHED_EXCEPTION_CODE = "400004";
    public static final String LOGIN_INFO_NOT_MATCHED_EXCEPTION_MESSAGE = "로그인정보 오류";


    public static final String DATA_NOT_FOUND_EXCEPTION_CODE = "400006";
    public static final String DATA_NOT_FOUND_EXCEPTION_MESSAGE = "먼저 scrap요청을 해주세요.";
}
