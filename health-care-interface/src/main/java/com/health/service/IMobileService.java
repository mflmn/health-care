package com.health.service;

import com.health.entity.Result;
import com.health.pojo.Setmeal;

import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IMobileService {
    Map findOrderById(Integer id) throws Exception;

    /**
     * 体检预约
     *
     * @param map
     * @return
     * @throws Exception
     */
    Result order(Map map) throws Exception;

    Setmeal findSetmealById(Integer id);

    List<Setmeal> findAllSetmeal();
}
