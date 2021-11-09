package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.CheckGroup;

import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface CheckGroupDao {
    List<CheckGroup> findAll();

    void deleteAssoc(Integer id);

    void deleteById(Integer id);

    Page<CheckGroup> selectByCondition(String queryString);

    CheckGroup findById(Integer id);

    List<Integer> findCheckItemIdsByCheckGroupId(Integer id);

    void deleteAssociation(Integer id);

    void edit(CheckGroup checkGroup);

    void add(CheckGroup checkGroup);

    void setCheckGroupAndCheckItem(Map map);
}
