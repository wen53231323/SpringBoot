package com.wen.测试增删改查;

import com.wen.MyBatisPlusTestApplication;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest注解：标识测试类
@SpringBootTest(classes = MyBatisPlusTestApplication.class)
public class 添加 {
    // 自动装配：注入你要测试的对象
    @Autowired
    private UserMapper userMapper;

    // 测试插入一条数据
    // MyBatis-Plus在实现插入数据时，会默认基于雪花算法的策略生成id
    // 最终执行的结果，所获取的id为1558128986359021569
    @Test
    void TestInsert() {
        User user = new User(null, "小明", "123", "男", 18, "123@qq.com");
        // 执行插入，返回受影响的行数
        int result = userMapper.insert(user);
        System.out.println("受影响的行数为：" + result);
        // 如果result大于0说明添加成功，打印 添加成功，否则添加失败
        System.out.println(result > 0 ? "添加成功！" : "添加失败！");
        // 当前 id 为雪花算法自动生成的id
        System.out.println("获取基于雪花算法生成的id：" + user.getId());
    }

}

