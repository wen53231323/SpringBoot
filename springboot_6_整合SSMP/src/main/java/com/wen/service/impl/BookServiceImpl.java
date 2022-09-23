package com.wen.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.dao.BookDao;
import com.wen.pojo.Book;
import com.wen.service.BookService;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.security.auth.callback.LanguageCallback;
import java.util.List;

@Service
public class BookServiceImpl implements BookService {

    // @Autowired注解：自动装配，注入对象
    @Autowired
    private BookDao bookDao;

    @Override
    public Boolean save(Book book) {
        // bookDao.insert(book)返回的是影响的行计数，让他>0,就说明有影响的数据，就返回true
        return bookDao.insert(book) > 0;
    }

    @Override
    public Boolean update(Book book) {
        return bookDao.updateById(book) > 0;
    }

    @Override
    public Boolean delete(Integer id) {
        return bookDao.deleteById(id) > 0;
    }

    @Override
    public Book getById(Integer id) {
        return bookDao.selectById(id);
    }

    @Override
    public List<Book> getAll() {
        // null说明查询的时候没有条件，那这就是 查询全部数据
        return bookDao.selectList(null);
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize) {
        IPage page = new Page(currentPage, pageSize);
        // null是查询条件
        bookDao.selectPage(page, null);
        return page;
    }

    @Override
    public IPage<Book> getPage(int currentPage, int pageSize, Book book) {

        // 功能等同于QueryWrapper，提供了Lambda表达式的语法可以避免填错列名
        LambdaQueryWrapper<Book> lqw = new LambdaQueryWrapper<>();

        // 如果获取不为空，则模糊查询
        lqw.like(Strings.isNotEmpty(book.getType()), Book::getType, book.getType());
        lqw.like(Strings.isNotEmpty(book.getName()), Book::getName, book.getName());
        lqw.like(Strings.isNotEmpty(book.getDescription()), Book::getDescription, book.getDescription());

        IPage page = new Page(currentPage, pageSize);
        // lqw是查询条件
        IPage pages = bookDao.selectPage(page, lqw);
        return pages;
    }
}
