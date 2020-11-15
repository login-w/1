package com.wsx.blog.service.adminService.blog;

import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Tag;
import com.wsx.blog.query.SearchBlog;

import java.util.List;

public interface BlogService {

    public void saveBlog(Blog blog);

    public void deleteBlog(int id);

    public void updateBlog(Blog blog);

    public Blog getById(int id);

    public List<Blog> getAllBlog();

    public List<Blog> getByTypeId(int type_id);

    public List<Blog> getBlogBySearch(SearchBlog searchBlog);

    public void saveBlogAndTag(Blog blog);

    public void updateView(Blog blog);

    public List<Blog> getBlogBySearchPage(SearchBlog searchBlog);

    public List<Blog> getRecomments();



    public void save(Blog blog);

    public List<Tag> getTags(int id);
//    public List<Blog> getByParam(Blog blog);



}
