package com.wsx.blog.dao;

import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Tag;
import com.wsx.blog.query.SearchBlog;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PreBlogDao {


//    @Insert("insert into t_blog(title,content,appreciation,commentabled,published,recommed,type_id,create_time,views,update_time,flag,first_picture) values(#{title},#{content}," +
//            "#{appreciation},#{commentabled},#{published},#{recommed},#{type.id},#{createTime},#{views},#{updateTime},#{flag},#{firstPicture})")
    public void saveBlog(Blog blog);

//    @Delete("delete from t_blog where id=#{id}")
    public void deleteBlog(int id);

    /*@Update("update t_blog set appreciation=#{appreciation},commentabled=#{commentabled},content=#{content}," +
            "createTime=#{createTime},first_picture=#{firstPicture},flag=#{flag},published=#{published}," +
            "recommed=#{recommed},title=#{title},update_time=#{updateTime},views=#{views}," +
            "type_id=#{type.id},user_id=#{user.id} where id=#{id}")*/
    public void updateBlog(Blog blog);

//    @Select("select * from t_blog where id=#{id}")
    public Blog getById(int id);

//    @Select("SELECT * FROM t_blog")
//    @Result(property = "type",column = "type_id",
//            one=@One(select = "com.wsx.blog.dao.BlogDao.getTypeByTypeId",fetchType= FetchType.EAGER))
    public List<Blog> getAllBlog();

    public List<Blog> getByTypeId(int type_id);

    public List<Blog> getBlogBySearch(SearchBlog searchBlog);
//    @Select("select * from t_blog where title like '${title}' or type_id=#{type.getId()} or recommed=#{recommed}")
//    public List<Blog> getByParam(Blog blog);

//    @Select("select * from t_blog where if test")


    public void saveBlogAndTag(Blog blog, int tag_id);

    public List<Tag> getTags(int id);

    public void updateView(Blog blog);

    public List<Blog> getBlogBySearchPage(SearchBlog searchBlog);


    public List<Blog> getRecomments();



}
