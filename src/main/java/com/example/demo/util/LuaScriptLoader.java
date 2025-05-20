package com.example.demo.util;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.scripting.support.ResourceScriptSource;


public class LuaScriptLoader {
    public static DefaultRedisScript<Long> loadStockDeductScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/stock_deduct.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
    public static DefaultRedisScript<Long> loadReleaseLockScript() {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript<>();
        redisScript.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/release_lock.lua")));
        redisScript.setResultType(Long.class);
        return redisScript;
    }
}
