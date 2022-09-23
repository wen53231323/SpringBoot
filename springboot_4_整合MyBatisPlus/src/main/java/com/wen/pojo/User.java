package com.wen.pojo;

import com.baomidou.mybatisplus.annotation.*;

// @TableName注解：设置实体类对应的表名
@TableName("t_user")
public class User {
    // @TableId注解：将某个属性对应的字段标识为主键
    // value属性：用于指定表中的主键字段
    // type属性：设置主键生成策略
    @TableId(value = "id", type = IdType.AUTO)
    private Long id; // id
    // @TableField注解：设置属性所对应的字段名
    @TableField("username")
    private String username; // 用户名
    private String password; // 密码
    private String gender; // 性别
    private Integer age; // 年龄
    private String email; // 邮箱
    // @TableLogic注解：设置逻辑删除
    // @TableLogic
    // private Integer isDelted; // 逻辑删除

    public User() {
    }

    public User(Long id, String username, String password, String gender, Integer age, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.email = email;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", gender='" + gender + '\'' +
                ", age=" + age +
                ", email='" + email + '\'' +
                '}';
    }
}
