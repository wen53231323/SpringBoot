package com.wen.dao;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.pojo.Book;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class BookDaoTestCase {

    @Autowired
    private BookDao bookDao;

    @Test
    void testGetById() {
        System.out.println(bookDao.selectById(1));
    }

    @Test
    void testSave() {
        Book book = new Book();
        book.setType("测试数据123");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookDao.insert(book);
    }

    @Test
    void testUpdate() {
        Book book = new Book();
        book.setId(17);
        book.setType("测试数据abcdefg");
        book.setName("测试数据123");
        book.setDescription("测试数据123");
        bookDao.updateById(book);
    }

    @Test
    void testDelete() {
        bookDao.deleteById(16);
    }

    @Test
    void testGetAll() {
        bookDao.selectList(null);
    }

    @Test
    void testGetPage() {
        // 设置分页，Page(当前页码,每页条数)
        IPage page = new Page(2, 5);
        bookDao.selectPage(page, null);
        // 当前页码值
        System.out.println("当前页码值" + page.getCurrent());
        // 每页显示数
        System.out.println("每页显示数" + page.getSize());
        // 数据总量
        System.out.println("数据总量" + page.getTotal());
        // 总页数
        System.out.println("总页数" + page.getPages());
        // 详细数据
        System.out.println("详细数据" + page.getRecords());
    }

    @Test
    void testGetBy() {
        // QueryWrapper对象：用于封装查询条件的对象，该对象可以动态使用API调用的方法添加条件，最终转化成对应的SQL语句。
        QueryWrapper<Book> qw = new QueryWrapper<>();
        // 使用QueryWapper对象直接调用对应条件操作
        qw.like("name", "Spring");
        bookDao.selectList(qw);
    }

    @Test
    void testGetBy1() {
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(Book::getName, "Spring");
        bookDao.selectList(lqw);
    }

    // 方式一：JAVA代码控制判断值不为null
    @Test
    void testGetBy2() {
        String name = null;
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        if(name != null) lqw.like(Book::getName,name);
        bookDao.selectList(lqw);
    }

    //方式二： API接口提供控制开关判断值不为null
    @Test
    void testGetBy3() {
        String name = null;
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<Book>();
        lqw.like(name != null, Book::getName, name);
        bookDao.selectList(lqw);
    }
}










