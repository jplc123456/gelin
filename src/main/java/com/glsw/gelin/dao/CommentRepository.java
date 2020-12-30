package com.glsw.gelin.dao;

import com.glsw.gelin.po.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment,Long> {


    List<Comment> findByBlogIdAndParentCommentsNull(Long blogId, Sort sort);
}
