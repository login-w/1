package com.wsx.blog.dao;

import com.wsx.blog.entity.Tag;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TagDao {

//    @Insert("insert into t_tag(name) values(#{name})")
    public void saveTag(Tag tag);

//    @Delete("delete from t_tag where id=#{id}")
    public void deleteTag(Tag tag);

//    @Update("update t_tag set name=#{name} where id=#{id}")
    public void updateTag(Tag tag);

//    @Select("select * from t_tag where id=#{id}")
    public Tag getById(int id);

//    @Select("select * from t_tag")
    public List<Tag> getAllTag();

//    @Select("select * from t_tag where name=#{name} ")
    public Tag getByName(String name);



}
