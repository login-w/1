package com.wsx.blog.service.comment;

import com.wsx.blog.dao.CommentDao;
import com.wsx.blog.entity.Comment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommentServiceImpl implements CommentSevice {
    @Autowired
    private CommentDao commentDao;
    @Value("${comment.avatar}")
    private String avatar;

    @Override
    public List<Comment> listCommentByBlogId(int blogId) {
         return commentDao.listCommentByBlogId(blogId);
    }

    @Override
    public void saveComment(Comment comment) {
        comment.setCreateTime(new Date());
        comment.setAvatar(avatar);
//        System.out.println("开始保存评论了....");
        commentDao.saveComment(comment);
    }

    @Override
    public List<Comment> getParentComments(int blogId) {
        return commentDao.getParentComments(blogId);
    }

    @Override
    public List<Comment> getChildComments(int blogId) {
        return commentDao.getChildComments(blogId);
    }

    @Override
    public List<Comment> getParent(int blogId) {
        List<Comment> parentComments = commentDao.getParentComments(blogId);
        List<Comment> childComments = commentDao.getChildComments(blogId);

        for (Comment parentComment : parentComments) {
            List<Comment> comments = new ArrayList<>();
            for (Comment childComment : childComments) {
                Comment temp=childComment;
                while (temp.getParentComment()!=null &&temp.getParentComment().getId()!=parentComment.getId()){
                    temp=temp.getParentComment();
                }
                if (temp.getParentComment()!=null){
                    comments.add(childComment);
                }
            }
            parentComment.setReplyComments(comments);
        }
        return parentComments;
    }
}
