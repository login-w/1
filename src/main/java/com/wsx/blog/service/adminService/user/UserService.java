package com.wsx.blog.service.adminService.user;

import com.wsx.blog.entity.User;

public interface UserService {
    public User checkUser(String username, String password);
}
