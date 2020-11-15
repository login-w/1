package com.wsx.blog.service.adminService.type;

import com.wsx.blog.entity.Type;
import com.wsx.blog.entity.TypeBlogNums;

import java.util.List;

public interface TypeService {
    public void saveType(Type type);
    public void deleteType(Type type);
    public void updateType(Type type);
    public Type getById(int id);

    public List<Type> getAllType();
    public Type  getByName(String name);

    public List<TypeBlogNums> getTypeBlogNums();


//    测试分页插件
//public Result getAll(String curPage, String pageNum);

}
