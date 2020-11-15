package com.wsx.blog.service.adminService.tag;

import com.wsx.blog.dao.TagDao;
import com.wsx.blog.entity.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao dao;

    @Override
    public void saveTag(Tag tag) {
        dao.saveTag(tag);
    }

    @Override
    public void deleteTag(Tag tag) {
        dao.deleteTag(tag);
    }

    @Override
    public void updateTag(Tag tag) {
        dao.updateTag(tag);
    }

    @Override
    public Tag getById(int id) {
        return dao.getById(id);
    }

    @Override
    public List<Tag> getAllTag() {
        return dao.getAllTag();
    }

    @Override
    public Tag getByName(String name) {
        return dao.getByName(name);
    }
}
