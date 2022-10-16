package com.xzp.service;

import com.xzp.entity.Comment;

import java.util.List;

/**
 * @Author xuezhanpeng
 * @Date 2022/10/6 9:29
 * @Version 1.0
 */
public interface CommentService {

    List<Comment> listCommentByBlogId(Long blogId);

    int saveComment(Comment comment);
    int deleteComment(Comment comment,Long id);

    Comment getCommentByid(Long id);
}
