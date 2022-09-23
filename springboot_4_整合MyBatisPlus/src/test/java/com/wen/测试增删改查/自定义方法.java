package com.wen.测试增删改查;

import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class 自定义方法 {
    // 自动装配：注入你要测试的对象
    @Autowired
    private UserMapper userMapper;

    // 根据id查询用户信息为Map集合
    @Test
    public void Test(){
        Map<String, Object> stringObjectMap = userMapper.selectMapById(1558133578480209921L);
        System.out.println(stringObjectMap);
    }
}
