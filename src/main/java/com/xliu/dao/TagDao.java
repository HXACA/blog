package com.xliu.dao;

import com.xliu.bean.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 17:29
 */
public interface TagDao extends JpaRepository<Tag,Long> {
    Tag findByName(String name);
}
