package com.wen.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.pojo.Book;

// 添加非通用操作API接口
public interface BookService_TestMyBatisPlus extends IService<Book> {
    // 功能追加，要避免与提供的接口冲突
    boolean saveBook(Book book);
}
