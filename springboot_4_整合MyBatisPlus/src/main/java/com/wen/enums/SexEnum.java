package com.wen.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;

public enum SexEnum {
    MALE(1, "男"),
    FEMALE(2, "女");

    // @EnumValue注解：将标识的属性值存储到数据库中
    @EnumValue
    private int sex;
    private String sexName;

    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }

    public int getSex() {
        return sex;
    }

    public String getSexName() {
        return sexName;
    }
}
