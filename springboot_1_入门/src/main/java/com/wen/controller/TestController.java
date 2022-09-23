package com.wen.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//Rest模式：@RestController与@GetMapping注解是基于Restful开发的典型注解
@RestController
@RequestMapping("/test")
public class TestController {
    @GetMapping
    public String getById(){
        System.out.println("Hello! SpringBoot!");
        return "Hello! SpringBoot!";
    }
}
