package com.wen.mapper;

import com.wen.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.awt.print.Book;

@Mapper
@Repository
public interface UserMapper {
    @Select("select * from t_user where id = #{id}")
    public User getById(Integer id);
}
