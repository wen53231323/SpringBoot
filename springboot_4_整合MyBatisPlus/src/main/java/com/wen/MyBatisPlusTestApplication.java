package com.wen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
// @MapperScan注解：扫描 指定 文件夹
@MapperScan("com.wen.mapper")
@SpringBootApplication
public class MyBatisPlusTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyBatisPlusTestApplication.class, args);
    }

}
