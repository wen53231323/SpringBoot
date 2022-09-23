package com.wen.调用Service层操作数据;

import com.wen.pojo.User;
import com.wen.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class 批量插入数据 {
    @Autowired
    private UserService userService;

    // 测试批量插入数据
    @Test
    public void test() {
        List<User> list = new ArrayList<>();
        for (int i = 1; i <= 10; i++) {
            User user = new User();
            user.setUsername("小明");
            user.setAge(20 + i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b ? "添加成功！" : "添加失败！");
    }
}
