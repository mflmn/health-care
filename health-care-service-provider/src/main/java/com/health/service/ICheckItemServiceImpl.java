package com.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.health.dao.CheckItemDao;
import com.health.entity.PageResult;
import com.health.pojo.CheckItem;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collections;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Service(interfaceClass = ICheckItemService.class, version = "1.0.0")
public class ICheckItemServiceImpl implements ICheckItemService {
    @Autowired
    private CheckItemDao checkItemDao;

    @Override
    public PageResult pageQuery(Integer currentPage, Integer pageSize, String queryString) {
        PageHelper.startPage(currentPage, pageSize);
        Page<CheckItem> page = checkItemDao.selectByCondition(queryString);
        return new PageResult(page.getTotal(), Collections.singletonList(page.getResult()));
    }

    @Override
    public void edit(CheckItem checkItem) {
        checkItemDao.edit(checkItem);
    }

    @Override
    public CheckItem findById(Integer id) {
        return checkItemDao.findById(id);
    }

    @Override
    public void delete(Integer id) throws RuntimeException {
        // 查询当前检查项是否和检查组关联
        long count = checkItemDao.findCountByCheckItemId(id);
        if (count > 0) {
            // 当前检查项被引用, 不能删除
            throw new RuntimeException("当前检查项被引用, 不能删除");
        }
        checkItemDao.deleteById(id);
    }

    @Override
    public void add(CheckItem checkItem) {
        checkItemDao.add(checkItem);
    }
}
