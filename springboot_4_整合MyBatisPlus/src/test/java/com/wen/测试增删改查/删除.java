package com.wen.测试增删改查;

import com.wen.MyBatisPlusTestApplication;
import com.wen.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// @SpringBootTest注解：标识测试类
@SpringBootTest(classes = MyBatisPlusTestApplication.class)
public class 删除 {
    // 自动装配：注入你要测试的对象
    @Autowired
    private UserMapper userMapper;

    // 测试根据id删除一条数据
    @Test
    public void testDeleteById() {
        // 执行删除，返回受影响的行数（传入的id末尾需要加上L）
        int result = userMapper.deleteById(1558130490629296130L);
        System.out.println("受影响的行数为：" + result);
        // 如果result大于0说明添加成功，打印删除成功，否则 打印删除失败
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
    }

    // 根据ID批量删除数据
    @Test
    public void testDeleteBatchIds() {
        // 创建List集合
        List<Long> ids = Arrays.asList(1558128986359021569L, 1558133209494708225L);
        // 根据List集合执行批量删除，返回受影响的行数
        int result = userMapper.deleteBatchIds(ids);
        System.out.println("受影响的行数为：" + result);
        // 如果result大于0说明添加成功，打印删除成功，否则 打印删除失败
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
    }

    // 根据Map集合中所设置的条件删除数据
    @Test
    public void testDeleteByMap() {
        // 当前演示为根据name和age删除数据
        // 执行SQL为：DELETE FROM user WHERE name = ? AND age = ?
        Map<String, Object> map = new HashMap<>();
        map.put("username", "小明");
        map.put("age", 18);
        // 根据Map集合执行批量删除，返回受影响的行数
        int result = userMapper.deleteByMap(map);
        System.out.println("受影响的行数为：" + result);
        // 如果result大于0说明添加成功，打印删除成功，否则 打印删除失败
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
    }
}
