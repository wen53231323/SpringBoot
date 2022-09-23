package com.wen.条件构造器;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class LambdaUpdateWrapperTest {
    @Autowired
    private UserMapper userMapper;
    //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
    @Test
    public void test(){
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getUsername, "a")
                .and(i -> i.gt(User::getAge, 20).or().isNull(User::getEmail));
        updateWrapper.set(User::getUsername, "小黑").set(User::getEmail,"abc@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println("result："+result);
    }
}
