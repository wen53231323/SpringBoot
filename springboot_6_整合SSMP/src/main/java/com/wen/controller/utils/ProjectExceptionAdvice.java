package com.wen.controller.utils;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

// -----------------------作为springmvc的异常处理器-----------------------
// @ControllerAdvice注解：将当前类标识为异常处理的组件
// @ControllerAdvice
// @RestControllerAdvice = @ResponseBody + @ControllerAdvice
@RestControllerAdvice
public class ProjectExceptionAdvice {
    // 拦截所有的异常信息
    @ExceptionHandler(Exception.class)
    public Result doException(Exception ex){
        // 记录日志，略
        // 通知运维，略
        // 发送邮件给开发人员,ex对象发送给开发人员
        ex.printStackTrace();
        return new Result("服务器故障，请稍后再试！");
    }
}
