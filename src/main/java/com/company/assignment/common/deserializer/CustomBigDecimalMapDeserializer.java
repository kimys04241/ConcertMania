package com.company.assignment.common.deserializer;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomBigDecimalMapDeserializer extends JsonDeserializer<List<Map<String, BigDecimal>>> {

    @Override
    public List<Map<String, BigDecimal>> deserialize(JsonParser jsonParser, DeserializationContext ctxt) throws IOException {
        List<Map<String, String>> rawList = jsonParser.readValueAs(new TypeReference<List<Map<String, String>>>() {});
        List<Map<String, BigDecimal>> resultList = new ArrayList<>(rawList.size());

        for (Map<String, String> entry : rawList) {
            entry.keySet().forEach(key -> {
                Map<String, BigDecimal> result = new HashMap<>();
                String value = entry.get(key);
                if (value != null && !value.trim().isEmpty()) {
                    result.put(key, new BigDecimal(value.replace(",", "")));
                }else {
                    result.put(key, BigDecimal.ZERO);
                }

                resultList.add(result);
            });

        }
        return resultList;
    }
}
