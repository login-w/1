package com.wsx.blog.service.adminService.tag;

import com.wsx.blog.entity.Tag;

import java.util.List;

public interface TagService {

    public void saveTag(Tag tag);
    public void deleteTag(Tag tag);
    public void updateTag(Tag tag);
    public Tag getById(int id);

    public List<Tag> getAllTag();
    public Tag  getByName(String name);
}
