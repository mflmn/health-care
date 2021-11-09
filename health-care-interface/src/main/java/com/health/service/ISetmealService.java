package com.health.service;

import com.health.entity.PageResult;
import com.health.pojo.Setmeal;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
public interface ISetmealService {
    Setmeal queryById(Integer setmealId);

    void edit(Setmeal setmeal);

    void delete(Integer setmealId);

    PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString);

    void add(Setmeal setmeal, Integer[] checkGroupIds);
}
