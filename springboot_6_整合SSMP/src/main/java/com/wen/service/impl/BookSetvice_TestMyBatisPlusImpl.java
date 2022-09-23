package com.wen.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wen.dao.BookDao;
import com.wen.pojo.Book;
import com.wen.service.BookService_TestMyBatisPlus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

//添加非通用操作API
@Service
public class BookSetvice_TestMyBatisPlusImpl extends ServiceImpl<BookDao, Book> implements BookService_TestMyBatisPlus {
    // @Autowired注解：自动装配，注入对象
    @Autowired
    private BookDao bookDao;

    @Override
    public boolean saveBook(Book book) {
        // bookDao.insert(book)返回的是影响的行计数，让他>0就说明有影响的数据，就返回true
        return bookDao.insert(book) > 0;
    }

}

