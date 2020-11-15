package com.wsx.blog.service.adminService.user;

import com.wsx.blog.dao.UserDao;
import com.wsx.blog.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao dao;

    @Override
    public User checkUser(String username, String password) {
        User user = dao.checkUser(username, password);
        return user;
    }
}
