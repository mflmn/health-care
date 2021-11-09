package com.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.dao.SetmealDao;
import com.health.entity.PageResult;
import com.health.pojo.Setmeal;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import redis.clients.jedis.JedisPool;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
@Transactional(rollbackFor = Exception.class)
@Service(interfaceClass = ISetmealService.class, version = "1.0.0")
public class ISetmealServiceImpl implements ISetmealService {
    @Autowired
    private SetmealDao setmealDao;
    @Autowired
    private JedisPool jedisPool;

    @Override
    public Setmeal queryById(Integer setmealId) {
        return setmealDao.findById(setmealId);
    }

    @Override
    public void edit(Setmeal setmeal) {
        setmealDao.updateById(setmeal);
        setmealDao.deleteAssoc(setmeal.getId());
    }

    @Override
    public void delete(Integer setmealId) {
        setmealDao.deleteById(setmealId);
        setmealDao.deleteAssoc(setmealId);
    }

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<Setmeal> page = setmealDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), Collections.singletonList(page.getResult()));
    }

    @Override
    public void add(Setmeal setmeal, Integer[] checkgroupIds) {
        setmealDao.add(setmeal);
        if (checkgroupIds != null && checkgroupIds.length > 0) {
            // 绑定套餐和检查组的多对多关系
            setSetmealAndCheckGroup(setmeal.getId(), checkgroupIds);
        }

        jedisPool.getResource().sadd("storage", setmeal.getImg());
    }

    private void setSetmealAndCheckGroup(Integer id, Integer[] checkgroupIds) {
        for (Integer checkgroupId : checkgroupIds) {
            Map<String, Integer> map = new HashMap<>();
            map.put("setmealId", id);
            map.put("checkgroupId", checkgroupId);
            setmealDao.setSetmealAndCheckGroup(map);
        }
    }
}
