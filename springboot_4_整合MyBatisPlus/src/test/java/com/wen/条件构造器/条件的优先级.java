package com.wen.条件构造器;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class 条件的优先级 {

    @Autowired
    private UserMapper userMapper;

    // ---------------------------条件的优先级---------------------------
    //将（年龄大于20并且用户名中包含有a）或邮箱为null的用户信息修改
    @Test
    public void test04() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("age", 20).like("username", "a").or().isNull("email");
        User user = new User();
        user.setUsername("小明");
        user.setEmail("123@qq.com");
        int result = userMapper.update(user, updateWrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }

    // 将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
    @Test
    public void test05() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        // lambda中的条件优先执行
        updateWrapper.like("username", "a").and(i -> i.gt("age", 20).or().isNull("email"));
        User user = new User();
        user.setUsername("小明");
        user.setEmail("123@qq.com");
        int result = userMapper.update(user, updateWrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }
}
