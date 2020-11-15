package com.wsx.blog.service.comment;

import com.wsx.blog.entity.Comment;

import java.util.List;

public interface CommentSevice {
    List<Comment> listCommentByBlogId(int blogId);

    void saveComment(Comment comment);

    List<Comment> getParentComments(int blogId);

    List<Comment> getChildComments(int blogId);

    List<Comment> getParent(int blogId);


}
