package com.wsx.blog.controller.type;


import com.wsx.blog.entity.Type;
import com.wsx.blog.service.adminService.type.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class TestType {
    @Autowired
    private TypeServiceImpl service;

    @RequestMapping("/saveType")
    public String test1(){
        Type type = new Type();
        type.setName("java");
        Type type1 = new Type();
        type1.setName("python");
        service.saveType(type);
        service.saveType(type1);
        return "保存成功了！！！";

    }
    @RequestMapping("deleteType")
    public String test2(){
        Type type=new Type();
        type.setId(18);
        type.setName("java");
        service.deleteType(type);
        return "删除成功了！！！！";
    }
    @RequestMapping("updateType")
    public String test3(){
        Type type=new Type();
        type.setName("php");
        type.setId(19);
        service.updateType(type);
        return "更新成功了！！！";
    }
    @RequestMapping("selectById")
    public String test4(){
        Type type = service.getById(19);
        return "查询结果为："+type.toString();
    }
    @RequestMapping("selectList")
    public String test5(){
        List<Type> allType = service.getAllType();
        return "查询结果集为："+allType.toString();
    }

//


}
