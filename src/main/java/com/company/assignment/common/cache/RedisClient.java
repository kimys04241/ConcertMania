package com.company.assignment.common.cache;

import lombok.AllArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@AllArgsConstructor
@Component
public class RedisClient {

    private final StringRedisTemplate redisTemplate;
    private final RedisTemplate<String, String> redisTemplateApiScrapData;

    public void saveData(String key, String value, long timeout) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        operations.set(key, value, timeout, TimeUnit.SECONDS);
    }

    public String getData(String key) {
        ValueOperations<String, String> operations = redisTemplate.opsForValue();
        return operations.get(key);
    }

    public void deleteData(String key) {
        redisTemplate.delete(key);
    }
}
