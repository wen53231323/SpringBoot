package com.wen;

import com.wen.Dao.BookDao;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


// 使用@SpringBootTest或@ContextConfiguration标识测试类
// classes属性：指定配置类
// 方式一
@SpringBootTest(classes = Springboot2JunitApplication.class)
// 方式二
//@ContextConfiguration(classes = Springboot2JunitApplication.class)
class Springboot2JunitApplicationTests {
    // 自动装配：注入你要测试的对象
    @Autowired
    private BookDao bookDao;

    @Test
    void contextLoads() {
        //执行要测试的对象对应的方法
        bookDao.test();
    }

}
