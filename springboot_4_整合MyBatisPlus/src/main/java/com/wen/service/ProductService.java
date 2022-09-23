package com.wen.service;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wen.pojo.Product;
// @DS注解：指定操作的数据源，slave_1为product表
@DS("slave_1")
// 继承IService模板提供的基础功能
public interface ProductService extends IService<Product> {

}
