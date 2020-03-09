package com.xliu.service.impl;

import com.xliu.NotFoundException;
import com.xliu.bean.Tag;
import com.xliu.dao.TagDao;
import com.xliu.service.TagService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 17:26
 */

@Service
public class TagServiceImple implements TagService {
    
    @Autowired
    TagDao tagDao;

    @Transactional
    @Override
    public Tag saveTag(Tag tag) {
        return tagDao.save(tag);
    }

    @Transactional
    @Override
    public Tag getTag(Long id) {
        return tagDao.findOne(id);
    }

    @Transactional
    @Override
    public Page<Tag> ListTag(Pageable pageable) {
        return tagDao.findAll(pageable);
    }

    @Transactional
    @Override
    public Tag updateTag(Long id, Tag tag) {
        Tag t = tagDao.findOne(id);
        if(t==null)
            throw new NotFoundException("不存在该类型");
        BeanUtils.copyProperties(tag,t);
        return tagDao.save(t);
    }

    @Transactional
    @Override
    public void deleteTag(Long id) {
        tagDao.delete(id);
    }

    @Override
    public Tag getTagByName(String name) {
        return tagDao.findByName(name);
    }

    @Override
    public List<Tag> getAllTags() {
        return tagDao.findAll();
    }

    private List<Long> convertToList(String ids){
        List<Long>list = new ArrayList<>();
        if(!"".equals(ids) && ids!=null){
            String[] idarray = ids.split(",");
            for(int i=0;i<idarray.length;i++){
                list.add(Long.valueOf(idarray[i]));
            }
        }
        return list;
    }

    @Override
    public List<Tag> listTag(String ids) {
        return tagDao.findAll(convertToList(ids)) ;
    }

    @Override
    public List<Tag> getTopTags(Integer size) {
        Sort sort = new Sort(Sort.Direction.DESC,"blogs.size");
        Pageable pageable = new PageRequest(0,size,sort);
        return tagDao.findTop(pageable);
    }
}
