package com.wen.测试增删改查;

import com.wen.MyBatisPlusTestApplication;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

// @SpringBootTest注解：标识测试类
@SpringBootTest(classes = MyBatisPlusTestApplication.class)
public class 修改 {
    // 自动装配：注入你要测试的对象
    @Autowired
    private UserMapper userMapper;

    @Test
    public void testUpdateById(){
        //执行SQL为： UPDATE user SET name=?, age=?, email=? WHERE id=?
        User user = new User();
        user.setId(1558133578480209921L);
        user.setGender("女");
        int result = userMapper.updateById(user);
        System.out.println("受影响的行数为：" + result);
        // 如果result大于0说明添加成功，打印 修改成功，否则修改失败
        System.out.println(result > 0 ? "修改成功！" : "修改失败！");
    }
}
