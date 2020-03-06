package com.xliu.dao;

import com.xliu.bean.Type;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 17:29
 */
public interface TypeDao extends JpaRepository<Type,Long> {
    Type findByName(String name);
}
