package com.wsx.blog.service.adminService.blog;

import com.wsx.blog.dao.BlogDao;
import com.wsx.blog.dao.TypeDao;
import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Tag;
import com.wsx.blog.entity.Type;
import com.wsx.blog.query.SearchBlog;
import com.wsx.blog.util.BeanUtil;
import com.wsx.blog.util.MarkdownUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    private BlogDao dao;
    @Autowired
    private TypeDao typeDao;
    @Override
    public void saveBlog(Blog blog) {
        dao.saveBlog(blog);
        Type type = typeDao.getById(blog.getType().getId());
        type.setBlognums(type.getBlognums()+1);
        typeDao.saveBlogNums(type);
    }


    @Override
    public void deleteBlog(int id) {
        Blog blog = dao.getById(id);
        dao.deleteBlog(id);
        Type type = typeDao.getById(blog.getType().getId());
        type.setBlognums(type.getBlognums()-1);
        typeDao.saveBlogNums(type);
    }

    @Override
    public void updateBlog(Blog blog) {
        Integer typeId = blog.getTypeId();
        Type type = typeDao.getById(typeId);
        blog.setType(type);
        blog.setUpdateTime(new Date());

        Blog byId = dao.getById(blog.getId());
        if (byId.getType().getId()!=blog.getType().getId()){
            dao.updateBlog(blog);
            Type type1 = typeDao.getById(byId.getType().getId());
            type1.setBlognums(type1.getBlognums()-1);
            typeDao.saveBlogNums(type1);
            Type type2 = typeDao.getById(blog.getType().getId());
            type2.setBlognums(type2.getBlognums()+1);
            typeDao.saveBlogNums(type2);
        }else{
            dao.updateBlog(blog);
        }


    }

    @Override
    public Blog getById(int id) {
        Blog blog = dao.getById(id);
        return blog;
    }

    @Override
    public List<Blog> getAllBlog() {
        return dao.getAllBlog();
    }

    @Override
    public List<Blog> getByTypeId(int type_id) {
       return dao.getByTypeId(type_id);
    }

    @Override
    public List<Blog> getBlogBySearch(SearchBlog searchBlog) {
       return dao.getBlogBySearch(searchBlog);
    }

    @Override
    public void saveBlogAndTag(Blog blog) {
        List<Tag> tags = blog.getTags();
        for (Tag tag : tags) {
            dao.saveBlogAndTag(blog,tag.getId());
        }
    }

    @Override
    public void updateView(Blog blog) {
        dao.updateView(blog);
    }

    @Override
    public List<Blog> getBlogBySearchPage(SearchBlog searchBlog) {
       return dao.getBlogBySearchPage(searchBlog);
    }

    @Override
    public List<Blog> getRecomments() {
        return dao.getRecomments();
    }

    @Override
//    @Transactional(propagation= Propagation.REQUIRED,isolation= Isolation.READ_COMMITTED)
    public void save(Blog blog) {
        this.saveBlog(blog);
        this.saveBlogAndTag(blog);
    }

    @Override
    public List<Tag> getTags(int id) {
        return dao.getTags(id);
    }

//    @Override
//    public List<Blog> getByParam(Blog blog) {
//        return dao.getByParam(blog);
//    }
}
