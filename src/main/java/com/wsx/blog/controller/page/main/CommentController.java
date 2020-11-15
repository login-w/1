package com.wsx.blog.controller.page.main;

import com.wsx.blog.entity.Blog;
import com.wsx.blog.entity.Comment;
import com.wsx.blog.entity.User;
import com.wsx.blog.service.adminService.blog.BlogService;
import com.wsx.blog.service.adminService.blog.BlogServiceImpl;
import com.wsx.blog.service.comment.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/page")
public class CommentController {
    @Autowired
    private BlogServiceImpl blogService;
    @Autowired
    private CommentServiceImpl commentService;

    @GetMapping("/comment{blogId}")
    public String comments(@PathVariable("blogId") int blogId,Model model){
        List<Comment> comments = commentService.getParent(blogId);
        model.addAttribute("comments",comments);

        return "page/blog::commentList";
    }


    @PostMapping("comment")
    public String post(Comment comment, HttpSession session){
        int id = comment.getBlog().getId();
        Blog blog = blogService.getById(id);
        comment.setBlog(blog);
        if (comment.getParentComment().getId()==-1){
            comment.getParentComment().setId(null);
        }
        User user = (User) session.getAttribute("user");
        if (user!=null){
            comment.setOwner(true);
            comment.setAvatar(user.getAvatar());
            comment.setNickName(user.getNickName());
            comment.setEmail(user.getEmail());
        }
        commentService.saveComment(comment);
//        System.out.println("****comment:****"+comment);
        return "redirect:/page/comment"+id;
    }

}
