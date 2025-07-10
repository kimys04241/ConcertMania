package com.company.assignment.common.util;

import com.company.assignment.common.api.ApiStatus;
import com.company.assignment.common.exception.RequiredFieldException;
import org.springframework.util.StringUtils;

public class RegNoFormatUtil {

    public static String regNoFormat(String regNo) {

        if (!StringUtils.hasText(regNo) || !regNo.matches("^\\d{6}-\\d{7}$")) {
            throw new RequiredFieldException(ApiStatus.REQUIRED_FIELD_EXCEPTION_CODE, ApiStatus.REQUIRED_FIELD_EXCEPTION_MESSAGE);
        }
        return regNo.replaceAll("-", "");
    }
}
