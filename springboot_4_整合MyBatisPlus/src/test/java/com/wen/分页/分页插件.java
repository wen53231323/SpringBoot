package com.wen.分页;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.apache.logging.log4j.util.Strings;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.print.Book;

@SpringBootTest
public class 分页插件 {

    @Autowired
    private UserMapper userMapper;

    @Test
    void testGetPage() {
        // QueryWrapper对象：用于封装查询条件的对象，该对象可以动态使用API调用的方法添加条件，最终转化成对应的SQL语句。
        LambdaQueryWrapper<User> lqw = new LambdaQueryWrapper<>();
        // 按照年龄降序排序（由高到低）
        lqw.orderByAsc(User::getAge);
        // 设置分页，Page(当前页码,每页条数)
        Page<User> page = new Page<>(1, 5);
        // 分页查询，null是查询条件
        userMapper.selectPage(page, null);
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
