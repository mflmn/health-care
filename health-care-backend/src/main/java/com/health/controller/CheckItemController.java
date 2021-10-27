package com.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.constant.MessageConstant;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.pojo.CheckItem;
import com.health.service.ICheckItemService;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RestController
@RequestMapping("/check-item")
public class CheckItemController {
    @Reference(version = "1.0.0")
    private ICheckItemService checkItemService;

    @PostMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkItemService.pageQuery(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }

    @PutMapping("/edit.do")
    public Result edit(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.edit(checkItem);
            return new Result(true, MessageConstant.EDIT_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKITEM_FAIL);
        }
    }

    @GetMapping("/findById.do")
    public Result findById(@RequestParam("id") Integer id) {
        try {
            CheckItem checkItem = checkItemService.findById(id);
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkItem);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @DeleteMapping("/delete.do")
    public Result delete(@RequestParam("id") Integer id) {
        try {
            checkItemService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKITEM_SUCCESS);
        } catch (RuntimeException e) {
            return new Result(false, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKITEM_FAIL);
        }
    }

    @PostMapping("/add.do")
    private Result add(@RequestBody CheckItem checkItem) {
        try {
            checkItemService.add(checkItem);
            return new Result(true, MessageConstant.ADD_CHECKITEM_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.ADD_CHECKITEM_FAIL);
        }
    }
}
