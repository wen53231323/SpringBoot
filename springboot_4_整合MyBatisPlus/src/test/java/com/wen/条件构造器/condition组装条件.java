package com.wen.条件构造器;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class condition组装条件 {

    @Autowired
    private UserMapper userMapper;


    @Test
    public void test1() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        // isNotBlank判断某个字符创是否不为空字符串、不为null、不为空白符
        if (StringUtils.isNotBlank(username)) {
            queryWrapper.like("username", username);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }

    @Test
    public void test2() {
        String username = "a";
        Integer ageBegin = null;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username), "username", username);
        queryWrapper.ge(ageBegin != null, "age", ageBegin);
        queryWrapper.le(ageEnd != null, "age", ageEnd);
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
