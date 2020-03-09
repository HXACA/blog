package com.xliu.service;

import com.xliu.bean.Comment;

import java.util.List;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/9 10:34
 */
public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);
    Comment saveComment(Comment comment);
}
