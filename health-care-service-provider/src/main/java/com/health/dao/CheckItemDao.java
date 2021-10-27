package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.CheckItem;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface CheckItemDao {
    Page<CheckItem> selectByCondition(String queryString);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    void deleteById(Integer id);

    long findCountByCheckItemId(Integer checkItemId);

    void add(CheckItem checkItem);
}
