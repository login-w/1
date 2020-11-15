package com.wsx.blog.dao;

import com.wsx.blog.entity.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface CommentDao {
    List<Comment> listCommentByBlogId(int blogId);

    void saveComment(Comment comment);

    List<Comment> getParentComments(int blogId);

    List<Comment> getChildComments(int blogId);

}
