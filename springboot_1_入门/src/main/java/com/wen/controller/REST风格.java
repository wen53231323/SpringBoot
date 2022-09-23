package com.wen.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class REST风格 {
    // 前端请求路径：/user
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    public String getAll() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：user/1
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    public String getById() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：user
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.POST)
    public String save() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：user/1
    @ResponseBody
    @RequestMapping(value = "/user/{id}", method = RequestMethod.DELETE)
    public String deleteById() {
        return "Hello! SpringBoot!";
    }

    // 前端请求路径：user
    @ResponseBody
    @RequestMapping(value = "/user", method = RequestMethod.PUT)
    public String put() {
        return "Hello! SpringBoot!";
    }
}
