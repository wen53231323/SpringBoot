package com.wen.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wen.pojo.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BookService {
    // 添加
    Boolean save(Book book);
    // 更新
    Boolean update(Book book);
    // 删除
    Boolean delete(Integer id);
    // 根据id查询
    Book getById(Integer id);
    // 查询所有
    List<Book> getAll();
    // 分页查询
    IPage<Book> getPage(int currentPage, int pageSize);
    // 分页查询，携带参数
    IPage<Book> getPage(@Param("currentPage") int currentPage, @Param("pageSize") int pageSize, Book book);
}
