package com.company.assignment.common.converter;

import com.company.assignment.common.util.PasswordUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class PasswordConverter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return PasswordUtil.hashPassword(attribute); // 암호화 후 저장
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        return dbData;
    }
}
