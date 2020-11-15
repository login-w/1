package com.wsx.blog.controller.type;


import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Type;
import com.wsx.blog.service.adminService.blog.BlogServiceImpl;
import com.wsx.blog.service.adminService.type.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TypeController {
    @Autowired
    private TypeServiceImpl service;
    @Autowired
    private BlogServiceImpl blogService;

//    利用mybatis的分页查找
//    @PageableDefault(size = 10,sort = {"id"},direction = Sort.Direction.DESC)
//                                   Pageable pageable
//    默认展示第一页的内容
    @RequestMapping("/types")
    public String getTypes(Model model){
        PageHelper.startPage(1,5);
        List<Type> types = service.getAllType();
        PageInfo<Type> pageInfo=new PageInfo<>(types);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }
//    获取某页的标签
    @RequestMapping("/types{num}")
    public String getByPage(@PathVariable("num") int num,Model model){
        PageHelper.startPage(num,5);
        List<Type> types = service.getAllType();
        PageInfo<Type> pageInfo=new PageInfo<>(types);
        model.addAttribute("types",types);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/types";
    }

//    删除标签
    @RequestMapping("/deleteType{id}")
    public String delType(@PathVariable("id") int id,RedirectAttributes attributes){
        Type type = new Type();
        type.setId(id);
        List<Blog> byTypeId = blogService.getByTypeId(id);
        if (byTypeId.isEmpty()){
            service.deleteType(type);
            attributes.addFlashAttribute("msg","删除成功！");
        }else{
            attributes.addFlashAttribute("msg","删除失败！该类别还在被博客引用...");
        }
        return "redirect:/admin/types";
    }
//    更改标签的名字
    @GetMapping("/updateType{id}")
    public String goUpdateType(@PathVariable("id")int id, Model model){
        Type type = service.getById(id);
        model.addAttribute("type",type);
        return "admin/type-input";
    }
    @PutMapping("/addType")
    public String updateType(Type type,RedirectAttributes attributes) {
        Type byId = service.getById(type.getId());
        if (byId.getName().equals( type.getName())) {
            attributes.addFlashAttribute("msg", "修改失败！您可能没有修改类别的内容哦");
        } else {
                Type byName = service.getByName(type.getName());
                if (byName == null) {
                    attributes.addFlashAttribute("msg","恭喜您！修改成功！");
                    service.updateType(type);
                }else{
                    attributes.addFlashAttribute("msg","修改失败，名字已经存在....");
                }
        }
        return "redirect:/admin/types";
    }

//    添加标签
    @GetMapping("/addType")
    public String goAddType(){
        return "admin/type-input";
    }
    @PostMapping("/addType")
    public String addType(Type type, RedirectAttributes attributes){
        Type resType = service.getByName(type.getName());
        if (resType==null){
            service.saveType(type);
            attributes.addFlashAttribute("msg","添加标签成功！");
        }else{
            attributes.addFlashAttribute("msg","标签已经存在了哟，快去直接使用它吧～");
        }
        return "redirect:/admin/types";

    }





}
