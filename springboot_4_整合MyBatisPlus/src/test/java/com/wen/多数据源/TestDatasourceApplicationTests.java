package com.wen.多数据源;

import com.wen.pojo.Product;
import com.wen.pojo.User;
import com.wen.service.ProductService;
import com.wen.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@SpringBootTest
public class TestDatasourceApplicationTests {
    @Autowired
    UserService userService;

    @Autowired
    ProductService productService;

    @Test
    void contextLoads() {
        User user = userService.getById(1L);
        Product product = productService.getById(1L);
        System.out.println("User = " + user);
        System.out.println("Product = " + product);
    }
}
