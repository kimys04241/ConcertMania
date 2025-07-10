package com.company.assignment.common.converter;

import com.company.assignment.common.util.CryptoUtil;
import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Converter;

@Converter
public class AES256Converter implements AttributeConverter<String, String> {

    @Override
    public String convertToDatabaseColumn(String attribute) {
        if (attribute == null) return null;
        return CryptoUtil.aesEncrypt(attribute); // 암호화 후 저장
    }

    @Override
    public String convertToEntityAttribute(String dbData) {
        if (dbData == null) return null;
        return CryptoUtil.aesDecrypt(dbData); // 복호화 후 조회
    }
}
