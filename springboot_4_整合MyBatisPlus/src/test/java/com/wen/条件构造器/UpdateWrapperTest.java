package com.wen.条件构造器;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class UpdateWrapperTest {
    @Autowired
    private UserMapper userMapper;

    //将用户名中包含有a并且（年龄大于20或邮箱为null）的用户信息修改
    @Test
    public void test() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.like("username", "a").and(i -> i.gt("age", 20).or().isNull("email")).set("email", "svip@qq.com");
        int result = userMapper.update(null, updateWrapper);
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
        System.out.println("受影响的行数为：" + result);
    }
}
