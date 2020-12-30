package com.glsw.gelin.service;

import com.glsw.gelin.po.Comment;

import java.util.List;

public interface CommentService {
    List<Comment> listCommentByBlogId(Long blogId);


    Comment saveComment(Comment comment);
}
