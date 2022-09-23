package com.wen.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/Redis")
public class RedisTestController {
    @Autowired
    private RedisTemplate redisTemplate;

    @GetMapping
    public String test(){
        //获取基本的 ValueOperations 操作对象（操作Redis的String类型）
        ValueOperations valueOperations = redisTemplate.opsForValue();

        // 设置当前的key以及value值
        valueOperations.set("key", "Hello!Redis!");

        // 获取对应键值，根据 key 查询对应的 value ，如果不存在，返回空（nil）
        String key = (String) valueOperations.get("key");
        System.out.println(valueOperations.get("key"));
        return key;

    }
}
