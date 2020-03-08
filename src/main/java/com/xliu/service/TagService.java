package com.xliu.service;

import com.xliu.bean.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 17:24
 */
public interface TagService {
    Tag saveTag(Tag Tag);

    Tag getTag(Long id);

    Page<Tag>ListTag(Pageable pageable);

    Tag updateTag(Long id, Tag Tag);

    void deleteTag(Long id);

    Tag getTagByName(String name);

    List<Tag> getAllTags();

    List<Tag> listTag(String ids);

}
