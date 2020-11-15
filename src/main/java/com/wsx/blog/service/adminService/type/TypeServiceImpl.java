package com.wsx.blog.service.adminService.type;

import com.wsx.blog.dao.BlogDao;
import com.wsx.blog.dao.TypeDao;
import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Type;
import com.wsx.blog.entity.TypeBlogNums;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TypeServiceImpl implements TypeService {
    @Autowired
    private TypeDao dao;
    @Autowired
    private BlogDao blogDaodao;

    @Override
    public void saveType(Type type) {
        dao.saveType(type);
    }

    @Override
    public void deleteType(Type type) {
        dao.deleteType(type);

    }

    @Override
    public void updateType(Type type) {
        dao.updateType(type);
    }

    @Override
    public Type getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Type> getAllType() {
        return dao.getAllType();
    }

    @Override
    public Type getByName(String name) {
        return dao.getByName(name);
    }

    @Override
    public List<TypeBlogNums> getTypeBlogNums() {
        List<TypeBlogNums> list=new ArrayList<>();
        List<Blog> blogs = blogDaodao.getAllBlog();
        List<Type> types = dao.getAllType();
        for (int i = 0; i < types.size(); i++) {
            int id = types.get(i).getId();
            TypeBlogNums typeBlogNums = new TypeBlogNums();
            typeBlogNums.setTypeId(id);
            typeBlogNums.setTypeName(types.get(i).getName());
            int count=0;
            for (int j = 0; j < blogs.size(); j++) {
                if (blogs.get(j).getType().getId()==id){
                    count++;
                }
            }
            typeBlogNums.setBlogNums(count);
            list.add(typeBlogNums);
        }
        return list;
    }

//    @Override
//    public Result getAll(String curPage, String pageNum) {
//        int curPageIn = Integer.parseInt(curPage);
//        int pageNumIn = Integer.parseInt(pageNum);
//
//        List<Type> all = dao.getAll(curPageIn, pageNumIn);
//        PageHelper.startPage(1,3);
//        PageInfo<Type> pi = new PageInfo<Type>(all);
//        Result result = new Result();
//        result.setCode(0);
//        result.setCount((int) pi.getTotal());
//        result.setData(all);
//        return result;
//    }
}
