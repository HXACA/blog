package com.xliu.service.impl;

import com.xliu.NotFoundException;
import com.xliu.bean.Blog;
import com.xliu.bean.Type;
import com.xliu.dao.BlogDao;
import com.xliu.service.BlogService;
import com.xliu.util.MarkDownUtils;
import com.xliu.vo.BlogQuery;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.*;
import java.util.*;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/7 10:12
 */
@Service
public class BlogServiceImpl implements BlogService {

    @Autowired
    BlogDao blogDao;

    @Override
    public Blog getBlog(Long id) {
        return blogDao.findOne(id);
    }

    @Transactional
    @Override
    public Blog getAndConvertBlog(Long id) {
        Blog blog = blogDao.findOne(id);
        if(blog == null)
            throw new NotFoundException("该博客不存在");
        blogDao.updateViews(id);
        Blog b = new Blog();
        BeanUtils.copyProperties(blog,b);
        b.setContent(MarkDownUtils.markdownToHtmlExtensions(b.getContent()));
        return b;
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, BlogQuery blog) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                //构建条件组合
                if(!"".equals(blog.getTitle()) && blog.getTitle() !=null){
                    predicates.add(cb.like(root.<String>get("title"),"%"+blog.getTitle()+"%"));
                }
                if(blog.getTypeId()!=null){
                    predicates.add(cb.equal(root.<Type>get("type").get("id"),blog.getTypeId()));
                }
                if(blog.isRecommend()){
                    predicates.add(cb.equal(root.<Boolean>get("recommend"),blog.isRecommend()));
                }
                cq.where(predicates.toArray(new Predicate[predicates.size()]));
                return null;
            }
        },pageable);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable) {
        return blogDao.findAll(pageable);
    }

    @Override
    public Page<Blog> listBlog(String query,Pageable pageable) {
        return blogDao.findByQuery(query,pageable);
    }

    @Override
    public List<Blog> listRecommendBlog(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"updateTime");
        Pageable pageable = new PageRequest(0,size,sort);
        return blogDao.findTop(pageable);
    }

    @Transactional
    @Override
    public Blog saveBlog(Blog blog) {
        if(blog.getId() == null){
            blog.setCreateTime(new Date());
            blog.setUpdateTime(new Date());
            blog.setViews(0);
            return blogDao.save(blog);
        }else{
            blog.setUpdateTime(new Date());
            return updateBlog(blog.getId(),blog);
        }

    }

    @Transactional
    @Override
    public Blog updateBlog(Long id, Blog blog) {
        Blog b = blogDao.findOne(id);
        blog.setViews(b.getViews());
        blog.setCreateTime(b.getCreateTime());
        if(b==null){
            throw new NotFoundException("该博客不存在");
        }
        BeanUtils.copyProperties(blog,b);
        return blogDao.save(b);
    }

    @Transactional
    @Override
    public void deleteBlog(Long id) {
        blogDao.delete(id);
    }

    @Override
    public Page<Blog> listBlog(Pageable pageable, Long tagId) {
        return blogDao.findAll(new Specification<Blog>() {
            @Override
            public Predicate toPredicate(Root<Blog> root, CriteriaQuery<?> cq, CriteriaBuilder cb) {
                Join join = root.join("tags");
                return cb.equal(join.get("id"),tagId);
            }
        },pageable);
    }

    @Override
    public Map<String, List<Blog>> archiveBlog() {
        List<String>years = blogDao.findGroupYear();
        Map<String,List<Blog>> map = new HashMap<>();
        for (String year : years) {
            map.put(year,blogDao.findByYear(year));
        }
        return map;
    }

    @Override
    public Long countBlog() {
        return blogDao.count();
    }


}
