package com.annm.zilliqa_project.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

    @Autowired
    private RedisTemplate template;

    public void deleteAllData() {
        template.getConnectionFactory().getConnection().flushAll();
    }
}
