package com.glsw.gelin.service;

import com.glsw.gelin.po.Type;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface TypeService {
    /*---分类新增---*/
    Type saveType(Type type);
    /*---分类查询---*/
    Type getType(Long id);
    /*---通过名称查询分类---*/
    Type getNameType(String name);

    List<Type> listType();

    List<Type> listTypeTop(Integer size);
    /*---分页查询---*/
    Page<Type> listType(Pageable pageable);
    /*---分类修改---*/
    Type updateType(Long id,Type type);
    /*---分类删除---*/
    void deleteType(Long id);
}
