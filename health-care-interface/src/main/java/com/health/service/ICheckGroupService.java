package com.health.service;

import com.health.entity.PageResult;
import com.health.pojo.CheckGroup;

import java.util.List;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface ICheckGroupService {
    List<CheckGroup> findAll();

    void delete(Integer id);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void edit(CheckGroup checkGroup,Integer[] checkitemIds);

    void add(CheckGroup checkGroup, Integer[] checkItemIds);
}
