package com.wen.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wen.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Map;

@Mapper
@Repository
public interface UserMapper extends BaseMapper<User> {

    // 根据id查询用户信息为Map集合
    Map<String, Object> selectMapById(@Param("id") Long id);

    // 自定义分页，Mybatis-Puls提供的分页对象必须在第一个参数的位置
    // 通过姓名查询信息，并分页
    Page<User> selectPageByAge(@Param("page") Page<User> page, @Param("username")String username);

}
