package com.wen.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.wen.pojo.Product;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface ProductMapper extends BaseMapper<Product> {

}
