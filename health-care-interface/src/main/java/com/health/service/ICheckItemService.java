package com.health.service;

import com.health.entity.PageResult;
import com.health.pojo.CheckItem;

import java.util.List;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface ICheckItemService {
    List<CheckItem> findAll();

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void edit(CheckItem checkItem);

    CheckItem findById(Integer id);

    void delete(Integer id);

    void add(CheckItem checkItem);
}
