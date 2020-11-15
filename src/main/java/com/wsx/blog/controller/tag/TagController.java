package com.wsx.blog.controller.tag;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wsx.blog.entity.Tag;
import com.wsx.blog.service.adminService.tag.TagServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TagController {
    @Autowired
    private TagServiceImpl service;

    @RequestMapping("/tags")
    public String getTags(Model model){
        String orderBy="id"+" asc";

        PageHelper.startPage(1,5,orderBy);
        List<Tag> tags = service.getAllTag();
        PageInfo<Tag> pageInfo =new PageInfo<>(tags);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }
    @RequestMapping("/tags{num}")
    public String getTagsBypage(@PathVariable("num") int num,Model model){
        PageHelper.startPage(num,5);
        List<Tag> tags = service.getAllTag();
        PageInfo<Tag> pageInfo =new PageInfo<>(tags);
        model.addAttribute("tags",tags);
        model.addAttribute("pageInfo",pageInfo);
        return "admin/tags";
    }

//    添加标签
    @GetMapping("/addTag")
    public String goAddTag(){
        return "admin/tags-input";
    }
    @PostMapping("/addTag")
    public String addType(Tag tag, RedirectAttributes attributes){
        Tag resTag = service.getByName(tag.getName());
        if (resTag==null){
            service.saveTag(tag);
            attributes.addFlashAttribute("msg","添加标签成功！");
        }else{
            attributes.addFlashAttribute("msg","标签已经存在了哟，快去直接使用它吧～");
        }
        return "redirect:/admin/tags";

    }
    //    更改标签的名字
    @GetMapping("/updateTag{id}")
    public String goUpdateTag(@PathVariable("id")int id, Model model){
        Tag tag = service.getById(id);
        model.addAttribute("tag",tag);
        return "admin/tags-input";
    }
    //    更改标签
    @PutMapping("/addTag")
    public String updateTag(Tag tag,RedirectAttributes attributes) {
        Tag byId = service.getById(tag.getId());
        if (byId.getName().equals( tag.getName())) {
            attributes.addFlashAttribute("msg", "修改失败！您可能没有修改类别的内容哦");
        } else {
            Tag byName = service.getByName(tag.getName());
            if (byName == null) {
                attributes.addFlashAttribute("msg","恭喜您！修改成功！");
                service.updateTag(tag);
            }else{
                attributes.addFlashAttribute("msg","修改失败，名字已经存在....");
            }
        }
        return "redirect:/admin/tags";
    }


    //    删除标签
    @RequestMapping("/deleteTag{id}")
    public String delTag(@PathVariable("id") int id){
        Tag tag = new Tag();
        tag.setId(id);
        service.deleteTag(tag);
        return "redirect:/admin/tags";
    }


}
