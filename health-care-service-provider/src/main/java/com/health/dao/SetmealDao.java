package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.Setmeal;

import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
public interface SetmealDao {
    Setmeal findById(Integer setmealId);

    void updateById(Setmeal setmeal);

    void deleteById(Integer setmealId);

    void deleteAssoc(Integer setmealId);

    Page<Setmeal> selectByCondition(String queryString);

    void add(Setmeal setmeal);

    void setSetmealAndCheckGroup(Map<String, Integer> map);
}
