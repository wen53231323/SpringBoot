package com.wen.controller;

import org.springframework.web.bind.annotation.*;

// 控制层组件，且类中方法可直接将java对象转换为json字符串并响应到浏览器
// @RestController = @Controller + @ResponseBody
@RestController
@RequestMapping("/users")
public class REST风格优化 {
    // 前端请求路径：/users
    // 处理get请求
    @GetMapping
    public String getAll() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：users/1
    // 处理get请求
    @GetMapping("/{id}")
    public String getById() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：users
    // 处理post请求
    @PostMapping
    public String save() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：users/1
    // 处理delete请求
    @DeleteMapping("/{id}")
    public String deleteById() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：users
    // 处理put请求
    @PutMapping
    public String put() {
        return "Hello! SpringBoot!";
    }
}
