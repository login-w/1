package com.wsx.blog.controller.blog;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Tag;
import com.wsx.blog.entity.Type;
import com.wsx.blog.entity.User;
import com.wsx.blog.query.SearchBlog;
import com.wsx.blog.service.adminService.blog.BlogServiceImpl;
import com.wsx.blog.service.adminService.tag.TagServiceImpl;
import com.wsx.blog.service.adminService.type.TypeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class BlogController {
    @Autowired
    private BlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private TagServiceImpl tagService;

    @RequestMapping("/blogs")
    public String getAll(Model model){
        PageHelper.startPage(1,5);
        List<Blog> blogs = blogService.getAllBlog();
//        for (Blog blog : blogs) {
//            System.out.println(blog);
//        }
        PageInfo<Blog> pageInfo =new PageInfo<>(blogs);
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);

        model.addAttribute("pageInfo",pageInfo);
//        System.out.println("---------------------------");
        return "admin/blogs";
    }

    @RequestMapping("/blogs/search")
    public String getAllSearch(SearchBlog searchBlog, Model model){
        if (searchBlog.getNum()==null){
            searchBlog.setNum(1);
        }
        PageHelper.startPage(searchBlog.getNum(),5);
//        List<Blog> blogs = blogService.getAllBlog();
        List<Blog> blogs = blogService.getBlogBySearch(searchBlog);
        PageInfo<Blog> pageInfo =new PageInfo<>(blogs);
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs::blogList";
    }


    @GetMapping("/blogs{num}")
    public String getAll(@PathVariable("num") int num, Model model){
        PageHelper.startPage(num,5);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo<Blog> pageInfo =new PageInfo<>(blogs);
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/blogs";
    }



    @GetMapping("saveBlog")
    public String goSaveBlog(Model model){
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
//        model.addAttribute("blog",new Blog());
        return "admin/blogs-input";
    }


    @PostMapping("/saveBlog")
    public String saveBlog(Blog blog, RedirectAttributes attributes, HttpSession session){
//        设置默认时间为当前
        blog.setCreateTime(new Date());
        blog.setUpdateTime(new Date());
//        设置默认流浪量为1
        blog.setViews(1);
//        把当前用户放到blog中用以标识那个用户写的一篇blog
        User user = (User) session.getAttribute("user");
        blog.setUser(user);

//        获取前端传来的typeId 通过其获取对应的type，然后把这个type赋给blog
        Type type = typeService.getById(blog.getTypeId());

        blog.setType(type);
//        获取tags并把其放到blog中
        String[] split = blog.getTagIds().split(",");
        List<Tag> tags=new ArrayList<>();
        for (String s : split) {
            Tag tag = tagService.getById(Integer.parseInt(s));
            tags.add(tag);
        }
        blog.setTags(tags);
//        把前端传来的blog保存到数据库
        blogService.saveBlog(blog);
        attributes.addAttribute("message","保存成功!");


        return "redirect:/admin/blogs";
    }



    @GetMapping("/updateBlog{id}")
    public String goUpdateBlog(@PathVariable("id") int id,Model model){

        Blog blog = blogService.getById(id);
        List<Tag> tags = blogService.getTags(id);
        model.addAttribute("blog",blog);
//        model.addAttribute("tagIds",tagIds);
        model.addAttribute("types",typeService.getAllType());
        model.addAttribute("tags",tagService.getAllTag());
        return "admin/blogs-input";
    }

    @PutMapping("/saveBlog")
    public String UpdateBlog(Blog blog){
        blogService.updateBlog(blog);
        return "redirect:/admin/blogs";
    }


    @GetMapping("/deleteBlog{id}")
    public String deleteBlog(@PathVariable("id") int id){
        blogService.deleteBlog(id);
        return "redirect:/admin/blogs";
    }






}
