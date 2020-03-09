package com.xliu.dao;

import com.xliu.bean.Comment;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/9 10:36
 */
public interface CommentDao extends JpaRepository<Comment,Long> {


    List<Comment> findByBlogIdAndParentCommentNull(Long blogId,Sort sort);
}
