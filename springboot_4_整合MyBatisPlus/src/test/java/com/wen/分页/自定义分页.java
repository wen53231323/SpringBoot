package com.wen.分页;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class 自定义分页 {
    @Autowired
    private UserMapper userMapper;

    @Test
    public void test(){
        Page<User> page = new Page<>(2,5);
        // 分页查询
        userMapper.selectPageByAge(page,"小明");
        // 是否有下一页
        System.out.println("是否有下一页："+page.hasNext());
        // 是否有上一页
        System.out.println("是否有上一页："+page.hasPrevious());
        // 当前页码值
        System.out.println("当前页码值：" + page.getCurrent());
        // 每页显示数
        System.out.println("每页显示数：" + page.getSize());
        // 总记录数（数据总量）
        System.out.println("总记录数：" + page.getTotal());
        // 总页面数
        System.out.println("总页面数：" + page.getPages());
        // 详细数据
        System.out.println("详细数据：" + page.getRecords());
    }
}
