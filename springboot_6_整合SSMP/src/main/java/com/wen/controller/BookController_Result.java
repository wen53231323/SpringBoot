package com.wen.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wen.controller.utils.Result;
import com.wen.pojo.Book;
import com.wen.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController_Result {
    // @Autowired注解：自动装配，注入对象
    @Autowired
    private BookService bookService;

    // 查询所有get
    @GetMapping
    public Result getAll() {
        List<Book> all = bookService.getAll();
        // 使用构造方法，设置Result对象的flag属性和data属性（查询一定成功，但不一定有数据）
        return new Result(true,all);
    }

    // 添加post，使用@RequestBody接收json数据
    @PostMapping
    public Result save(@RequestBody Book book) {
        Boolean save = bookService.save(book);
        // 根据添加成功与失败，返回不同的提示msg
        return new Result(save,save?"添加成功":"添加失败");
    }

    // 更新put，使用@RequestBody接收json数据
    @PutMapping
    public Result update(@RequestBody Book book) {
        Boolean update = bookService.update(book);
        // 使用构造方法设置Result对象的flag属性，其他属性默认为null
        return new Result(update);
    }

    // 删除delete， 使用@PathVariable接收路径参数
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable Integer id) {
        // 使用构造方法，设置Result对象的flag属性和data属性（查询一定成功，但不一定有数据）
        return new Result(true,bookService.delete(id));
    }

    // 根据id查询，使用@PathVariable接收路径参数
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id) {
        // 使用构造方法，设置Result对象的flag属性和data属性（查询一定成功，但不一定有数据）
        return new Result(true,bookService.getById(id));
    }

    // 分页获取所有，使用@PathVariable接收路径参数
    @GetMapping("/{currentPage}/{pageSize}")
    public Result getPage(@PathVariable int currentPage, @PathVariable int pageSize,Book book) {
        IPage<Book> page = bookService.getPage(currentPage, pageSize,book);
        // 如果当前页码值大于了总页码值，那么重新执行查询操作，使用最大页码值作为当前页码值
        if( currentPage > page.getPages()){
            page = bookService.getPage((int)page.getPages(), pageSize,book);
        }
        // 使用构造方法，设置Result对象的flag属性和data属性（查询一定成功，但不一定有数据）
        return new Result(true,page);
    }
}
