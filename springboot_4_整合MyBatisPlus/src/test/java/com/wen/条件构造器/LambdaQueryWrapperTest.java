package com.wen.条件构造器;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class LambdaQueryWrapperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), User::getUsername, username);
        queryWrapper.ge(ageBegin != null, User::getAge, ageBegin);
        queryWrapper.le(ageEnd != null, User::getAge, ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
