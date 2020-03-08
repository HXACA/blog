package com.xliu.service;

import com.xliu.bean.Blog;
import com.xliu.vo.BlogQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/7 10:08
 */
public interface BlogService {

    Blog getBlog(Long id);

    Page<Blog>listBlog(Pageable pageable, BlogQuery blogQuery);

    Blog saveBlog(Blog blog);

    Blog updateBlog(Long id,Blog blog);

    void deleteBlog(Long id);
}
