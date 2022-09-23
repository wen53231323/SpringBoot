package com.wen.通用枚举;

import com.wen.enums.SexEnum;
import com.wen.mapper.StudentMapper;
import com.wen.pojo.Student;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class EnumTest {
    @Autowired
    private StudentMapper studentMapper;

    @Test
    public void test() {
        Student student = new Student();
        student.setName("小明"); // 设置姓名
        student.setSex(SexEnum.MALE);// 设置性别
        int insert = studentMapper.insert(student);
        System.out.println(insert > 0 ? "添加成功！" : "添加失败！");
    }

}
