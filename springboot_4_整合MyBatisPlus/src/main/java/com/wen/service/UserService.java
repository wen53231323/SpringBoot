package com.wen.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.pojo.User;


// @DS注解：指定操作的数据源，master为user表
@DS("master")
// 继承IService模板提供的基础功能
public interface UserService extends IService<User> {

}
