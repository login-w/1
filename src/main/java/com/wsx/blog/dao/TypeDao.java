package com.wsx.blog.dao;

import com.wsx.blog.entity.Type;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TypeDao {
//    @Insert("insert into t_type(name) values(#{name})")
    public void saveType(Type type);

//    @Delete("delete from t_type where id=#{id}")
    public void deleteType(Type type);

//    @Update("update t_type set name=#{name} where id=#{id}")
    public void updateType(Type type);

//    @Select("select * from t_type where id=#{id}")
    public Type getById(int id);

//    @Select("select * from t_type")
    public List<Type> getAllType();

//    @Select("select * from t_type where name=#{name} ")
    public Type getByName(String name);


    public void saveBlogNums(Type type);



}
