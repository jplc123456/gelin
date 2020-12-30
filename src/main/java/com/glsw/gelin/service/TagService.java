package com.glsw.gelin.service;

import com.glsw.gelin.po.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TagService {
    /*---分类新增---*/
    Tag saveTag(Tag tag);
    /*---分类查询---*/
    Tag getTag(Long id);
    /*---通过名称查询分类---*/
    Tag getNameTag(String name);

    List<Tag> listTag();
    List<Tag> listTag(String ids);
    List<Tag> listTagTop(Integer size);


    /*---分页查询---*/
    Page<Tag> listTag(Pageable pageable);
    /*---分类修改---*/
    Tag updateTag(Long id,Tag type);
    /*---分类删除---*/
    void deleteTag(Long id);
}
