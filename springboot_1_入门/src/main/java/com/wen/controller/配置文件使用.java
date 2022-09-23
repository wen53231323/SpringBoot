package com.wen.controller;

import com.wen.config.MyDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class 配置文件使用 {


    // ====================使用@Value注解读取yaml单一数据====================
    @Value("${str}")
    private String str;

    @Value("${key.key1}")
    private String value1;

    @Value("${list[1]}")
    private String b;

    @GetMapping
    @RequestMapping("/test1")
    public String Test1() {
        System.out.println("字面量--->" + str);
        System.out.println("对象--->" + value1);
        System.out.println("数组--->" + b);
        return "Hello! SpringBoot!";
    }

    // ====================使用Environment对象读取全部数据====================
    // 使用@Autowired自动装配注解将所有的yaml数据封装到这个对象中
    @Autowired
    private Environment env;

    @GetMapping
    @RequestMapping("/test2")
    public String Test2() {
        System.out.println("字面量--->" + env.getProperty("str"));
        System.out.println("对象--->" + env.getProperty("key.key1"));
        System.out.println("数组--->" + env.getProperty("list[1]"));
        return "Hello! SpringBoot!";
    }

    // ====================使用注解@ConfigurationProperties读取对象数据====================
    @Autowired
    private MyDataSource myDataSource;
    @GetMapping
    @RequestMapping("/test3")
    public String Test3() {
        System.out.println(myDataSource);
        return "Hello! SpringBoot!";
    }

}
