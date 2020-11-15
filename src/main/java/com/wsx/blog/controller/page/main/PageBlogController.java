package com.wsx.blog.controller.page.main;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.blog.entity.*;
import com.wsx.blog.query.SearchBlog;
import com.wsx.blog.service.PreBlogService.PreBlogServiceImpl;
import com.wsx.blog.service.adminService.blog.BlogService;
import com.wsx.blog.service.adminService.blog.BlogServiceImpl;
import com.wsx.blog.service.adminService.tag.TagServiceImpl;
import com.wsx.blog.service.adminService.type.TypeServiceImpl;
import com.wsx.blog.service.comment.CommentServiceImpl;
import com.wsx.blog.util.BeanUtil;
import com.wsx.blog.util.MarkdownUtils;
import org.apache.ibatis.annotations.ResultMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/page")
public class PageBlogController {
    @Autowired
    private PreBlogServiceImpl blogService;

    @Autowired
    private TypeServiceImpl typeService;

    @Autowired
    private TagServiceImpl tagService;
    @Autowired
    private CommentServiceImpl commentService;

    @RequestMapping("/index")
    public String page(Model model){
        String sortBlog="update_time"+" desc";
        PageHelper.startPage(1,5,sortBlog);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo<Blog> pageInfo =new PageInfo<>(blogs);
//        String sortType="blogNums"+" desc";
//        PageHelper.startPage(1,5);
//        List<Type> types = typeService.getAllType();
//
//        PageHelper.startPage(1,3);
//        List<Tag> tags = tagService.getAllTag();
//        获取最新推荐的文章
        List<Blog> recomments = blogService.getRecomments();
        model.addAttribute("recommends",recomments);
//        model.addAttribute("tags",tags);
//        model.addAttribute("types",types);
        model.addAttribute("blogs",blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "page/index";
    }
    @GetMapping("/index{num}")
    public String page(@PathVariable("num")int num, Model model){
        String sortBlog="update_time"+" desc";
        PageHelper.startPage(num,5,sortBlog);
        List<Blog> blogs = blogService.getAllBlog();
        PageInfo<Blog> pageInfo =new PageInfo<>(blogs);

        List<Blog> recomments = blogService.getRecomments();
        model.addAttribute("recommends",recomments);

        model.addAttribute("blogs",blogs);
        model.addAttribute("pageInfo",pageInfo);
        return "page/index";
    }
    @GetMapping("/blog{id}")
    public String goBlogPage(@PathVariable("id") int id ,Model model){
        Blog blog = blogService.getById(id);
        blog.setViews(blog.getViews()+1);
        blogService.updateView(blog);

        String markdown = MarkdownUtils.markdownToHtmlExtensions(blog.getContent());
        blog.setContent(markdown);
        model.addAttribute("blog",blog);
        if(blog.getCommentabled()){
            List<Comment> comments = commentService.getParent(id);
            model.addAttribute("comments",comments);
        }
        return "page/blog";
    }

    @RequestMapping("/type")
    public String type(Model model){
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        PageInfo<Type> typeNums =new PageInfo<>(types);
        model.addAttribute("typeNums",typeNums);

        Type type = types.get(0);
        PageHelper.startPage(1,5);
        List<Blog> blogs = blogService.getByTypeId(type.getId());
//        blogs==null

        PageInfo<Blog> blogNums =new PageInfo<>(blogs);
        model.addAttribute("blogNums",blogNums);
//        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        return "page/type";
    }
    @GetMapping("/type{id}")
    public String typeList(@PathVariable("id") int id,Model model){
        List<Type> types = typeService.getAllType();
        model.addAttribute("types",types);
        PageInfo<Type> typeNums =new PageInfo<>(types);
        model.addAttribute("typeNums",typeNums);

        PageHelper.startPage(1,5);
        List<Blog> blogs = blogService.getByTypeId(id);
//        blogs==null

        PageInfo<Blog> blogNums =new PageInfo<>(blogs);
        model.addAttribute("blogNums",blogNums);
//        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);

        return "page/type";
    }

    @PostMapping("/type")
    public String typeOne(TypeList typeList,Model model){
        int  num=typeList.getNum();
        int typeId=typeList.getTypeId();
        PageHelper.startPage(num,5);
        List<Blog> blogList = blogService.getByTypeId(typeId);
        for (Blog blog : blogList) {
            System.out.println(blog);
        }
        PageInfo<Blog> blogNums =new PageInfo<>(blogList);
        model.addAttribute("blogs",blogList);
        model.addAttribute("blogNums",blogNums);
        return "page/type::typeList";
    }
    @PostMapping("/search")
    public String search(SearchBlog searchBlog,Model model){
        PageHelper.startPage(1,5);
        List<Blog> search = blogService.getBlogBySearchPage(searchBlog);
        model.addAttribute("blogs",search);
        PageInfo<Blog> pageInfo =new PageInfo<>(search);
        model.addAttribute("pageInfo",pageInfo);
        return "page/search";
    }



    @RequestMapping("/archives")
    public String archives(Model model){
        String sort="create_time"+" desc";
        PageHelper.orderBy(sort);
        List<Blog> blogs = blogService.getAllBlog();
        model.addAttribute("blogs",blogs);
        return "/page/archives";

    }


    @RequestMapping("/about")
    public String about(){
        return "/page/about";
    }

    @RequestMapping("/tag")
    public String tag(){
        return "/page/tags";
    }








}
