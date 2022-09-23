package com.wen.runner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 预加载类，容器启动成功后自动执行
 * */
@Component
// @Order注解：对预加载类执行顺序排序
@Order(value=2)
public class CommandLineRunnerTest2 implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("执行顺序 -> 2");
    }
}
