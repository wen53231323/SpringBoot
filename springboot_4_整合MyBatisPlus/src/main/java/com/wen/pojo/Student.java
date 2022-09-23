package com.wen.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import com.wen.enums.SexEnum;
// @TableName注解：设置实体类对应的表名
@TableName("t_student")
public class Student {
    // @TableId注解：将某个属性对应的字段标识为主键
    // value属性：用于指定表中的主键字段
    // type属性：设置主键生成策略
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // @TableField注解：设置属性所对应的字段名
    @TableField("name")
    private String name;

    // 枚举类型属性
    private SexEnum sex;

    public Student() {
    }

    public Student(Long id, String name, SexEnum sex) {
        this.id = id;
        this.name = name;
        this.sex = sex;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SexEnum getSex() {
        return sex;
    }

    public void setSex(SexEnum sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", sex=" + sex +
                '}';
    }
}
