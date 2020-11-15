package com.wsx.blog.util;

import com.wsx.blog.entity.Blog;

public class BeanUtil {
    public static Object beanCopy(Blog blog){
        try {
            Blog clone = blog.clone();
            return clone;
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }
}
