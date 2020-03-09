package com.xliu.service;

import com.xliu.bean.Blog;
import com.xliu.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/7 10:08
 */
public interface BlogService {

    Blog getBlog(Long id);

    Blog getAndConvertBlog(Long id);

    Page<Blog>listBlog(Pageable pageable, BlogQuery blogQuery);

    Page<Blog>listBlog(Pageable pageable);

    Page<Blog>listBlog(String query,Pageable pageable);

    List<Blog>listRecommendBlog(Integer size);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);

    Page<Blog> listBlog(Pageable pageable, Long tagId);

    Map<String,List<Blog>> archiveBlog();

    Long countBlog();
}
