package com.wen.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wen.pojo.Book;
import com.wen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

//@RestController
//@RequestMapping("/books")
public class BookController {
    // @Autowired注解：自动装配，注入对象
    @Autowired
    private BookService bookService;

    // 查询所有get
    @GetMapping
    public List<Book> getAll(){
        return bookService.getAll();
    }

    // 添加post，使用@RequestBody接收json数据
    @PostMapping
    public Boolean save(@RequestBody Book book){
        return bookService.save(book);
    }

    // 更新put，使用@RequestBody接收json数据
    @PutMapping
    public Boolean update(@RequestBody Book book){
        return bookService.update(book);
    }

    // 根据id删除delete， 使用@PathVariable接收路径参数
    @DeleteMapping("/{id}")
    public Boolean delete(@PathVariable Integer id){
        return bookService.delete(id);
    }

    // 根据id查询，使用@PathVariable接收路径参数
    @GetMapping("/{id}")
    public Book getById(@PathVariable Integer id){
        return bookService.getById(id);
    }

    // 分页获取所有，使用@PathVariable接收路径参数
    @GetMapping("/{currentPage}/{pageSize}")
    public IPage<Book> getPage(@PathVariable int currentPage, @PathVariable int pageSize){
        return bookService.getPage(currentPage,pageSize);
    }
}

















