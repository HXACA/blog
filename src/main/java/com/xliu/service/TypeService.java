package com.xliu.service;

import com.xliu.bean.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 17:24
 */
public interface TypeService {
    Type saveType(Type type);

    Type getType(Long id);

    Page<Type>ListType(Pageable pageable);

    Type updateType(Long id,Type type);

    void deleteType(Long id);

    Type getTypeByName(String name);

    List<Type> getAllType();

    List<Type> getTopType(Integer size);
}
