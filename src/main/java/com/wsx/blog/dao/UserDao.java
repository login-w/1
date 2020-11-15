package com.wsx.blog.dao;

import com.wsx.blog.entity.User;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {
//    @Select("select * from t_user where username=#{username} and password=#{password}")
    public User checkUser(@Param("username") String username,@Param("password") String password);
}
