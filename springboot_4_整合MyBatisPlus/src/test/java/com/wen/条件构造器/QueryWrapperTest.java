package com.wen.条件构造器;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wen.mapper.UserMapper;
import com.wen.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class QueryWrapperTest {

    @Autowired
    private UserMapper userMapper;

    // ---------------------------组装查询条件---------------------------
    //查询用户名包含a，年龄在20到30之间，邮箱信息不为null的用户信息
    @Test
    public void test01() {
        // QueryWrapper对象：用于封装查询条件的对象，该对象可以动态使用API调用的方法添加条件，最终转化成对应的SQL语句。
        QueryWrapper queryWrapper = new QueryWrapper<>();
        // 使用QueryWapper对象直接调用对应条件操作
        queryWrapper.like("username", "a");
        queryWrapper.between("age", 20, 30);
        queryWrapper.isNotNull("email");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    // ---------------------------组装排序条件---------------------------
    // 查询用户信息，按照年龄的降序排序，若年龄相同，则按照id升序排序
    @Test
    public void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age").orderByAsc("id");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }

    // ---------------------------组装删除条件---------------------------
    // 删除邮箱地址为null的用户信息
    @Test
    public void test03() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println(result > 0 ? "删除成功！" : "删除失败！");
        System.out.println("受影响的行数为：" + result);
    }

    // 组装select子句
    //查询用户的用户名、年龄、邮箱信息
    @Test
    public void test06() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("username", "age", "email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
    // 实现子查询
    // 查询id小于等于100的用户信息
    @Test
    public void test07(){
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from t_user where id <= 100");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
}
