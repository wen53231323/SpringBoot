package com.wen.controller.utils;


import lombok.Data;


// 代表get、set、toString、equals、hashCode等操作
@Data
public class Result {
    // 封装操作结果到flag中，Boolean类型（true成功、false失败，或使用Integer类型（1结尾成功、0结尾失败）
    private Boolean flag;
    // 封装数据到data属性中
    private Object data;
    // 封装特殊消息到message 属性（msg）中
    private String msg;

    public Result(){}

    // 运行成功
    public Result(Boolean flag){
        this.flag = flag;
    }

    // 运行成功，传递数据
    public Result(Boolean flag, Object data){
        this.flag = flag;
        this.data = data;
    }

    // 程序出现异常，提示报错
    public Result(Boolean flag, String msg){
        this.flag = flag;
        this.msg = msg;
    }

    // 程序出现异常，提示报错
    public Result(String msg){
        this.flag = false;
        this.msg = msg;
    }
}
