package com.wen;

import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;


// 使用@SpringBootTest标识测试类
@SpringBootTest(classes = Springboot2JunitApplication.class)
class Springboot2JunitApplicationTests {
    // 自动装配：注入你要测试的对象
    @Autowired
    private UserMapper userMapper;

    @Test
    void Test() {
        //执行要测试的对象对应的方法
        User byId = userMapper.getById(1);
        System.out.println(byId);
    }

}
