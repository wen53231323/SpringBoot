package com.wen.测试增删改查;

import com.wen.MyBatisPlusTestApplication;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @SpringBootTest注解：标识测试类
@SpringBootTest(classes = MyBatisPlusTestApplication.class)
public class 查询 {
    // 自动装配：注入你要测试的对象
    @Autowired
    private UserMapper userMapper;

    // 查询所有用户信息
    @Test
    void testSelectList() {
        List<User> users = userMapper.selectList(null);
        users.forEach(System.out::println);
    }

    // 根据ID查询用户信息
    @Test
    public void testSelectById() {
        User user = userMapper.selectById(1L);
        System.out.println(user);
    }

    // 根据多个ID查询多个用户信息
    @Test
    public void testSelectBatchIds() {
        // 执行SQL为：SELECT id,name,age,email FROM user WHERE id IN ( ? , ? , ? )
        List<Long> ids = Arrays.asList(1L, 2L, 3L);
        List<User> users = userMapper.selectBatchIds(ids);
        users.forEach(System.out::println);
    }

    // 根据Map条件查询用户信息
    @Test
    public void testSelectByMap() {
        // 执行SQL为：SELECT id,name,age,email FROM user WHERE age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("age", 18);
        List<User> users = userMapper.selectByMap(map);
        users.forEach(System.out::println);
    }

}
