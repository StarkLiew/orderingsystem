package com.example.orderingSystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisLockService {

    private final RedisTemplate<String, Object> redisTemplate;

    @Autowired
    public RedisLockService(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public boolean acquireLock(String key) {
        return Boolean.TRUE.equals(redisTemplate.opsForValue().setIfAbsent(key, "locked", 30, TimeUnit.SECONDS));
    }

    public void releaseLock(String key) {
        redisTemplate.delete(key);
    }
}
