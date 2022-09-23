package com.wen.调用Service层操作数据;

import com.wen.pojo.User;
import com.wen.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class 查询记录数 {

    @Autowired
    private UserService userService;

    // 测试查询记录数
    @Test
    public void testGetCount(){
        // 查询总记录数
        // 执行的SQL为：SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数：" + count);
    }

}
