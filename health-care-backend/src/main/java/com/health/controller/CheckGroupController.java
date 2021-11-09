package com.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.constant.MessageConstant;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.pojo.CheckGroup;
import com.health.service.ICheckGroupService;
import org.springframework.web.bind.annotation.*;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RestController
@RequestMapping("/check-group")
public class CheckGroupController {
    @Reference(version = "1.0.0")
    private ICheckGroupService checkGroupService;

    @GetMapping("/findAll.do")
    public Result findAll() {
        try {
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroupService.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(true, MessageConstant.QUERY_CHECKGROUP_FAIL);
    }

    @DeleteMapping("/delete.do")
    public Result delete(@RequestParam("id") Integer id) {
        try {
            checkGroupService.delete(id);
            return new Result(true, MessageConstant.DELETE_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.DELETE_CHECKGROUP_FAIL);
        }
    }

    @PostMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return checkGroupService.pageQuery(queryPageBean.getCurrentPage(), queryPageBean.getPageSize(), queryPageBean.getQueryString());
    }

    @GetMapping("/findById.do")
    public Result findById(@RequestParam("id") Integer id) {
        CheckGroup checkGroup = checkGroupService.findById(id);
        if (checkGroup != null) {
            return new Result(true, MessageConstant.QUERY_CHECKGROUP_SUCCESS, checkGroup);
        }
        return new Result(false, MessageConstant.QUERY_CHECKGROUP_FAIL);
    }

    @GetMapping("/findCheckItemIdsByCheckGroupId.do")
    public Result findCheckItemIdsByCheckGroupId(@RequestParam("id") Integer id) {
        try {
            return new Result(true, MessageConstant.QUERY_CHECKITEM_SUCCESS, checkGroupService.findCheckItemIdsByCheckGroupId(id));
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
        }
    }

    @PutMapping("/edit.do")
    public Result edit(@RequestBody CheckGroup checkGroup, @RequestParam("checkItemIds") Integer[] checkItemIds) {
        try {
            checkGroupService.edit(checkGroup, checkItemIds);
        } catch (Exception e) {
            return new Result(false, MessageConstant.EDIT_CHECKGROUP_FAIL);
        }
        return new Result(true, MessageConstant.EDIT_CHECKGROUP_SUCCESS);
    }

    @PostMapping("/add.do")
    public Result add(@RequestBody CheckGroup checkGroup, @RequestParam("checkItemIds") Integer[] checkItemIds) {
        try {
            checkGroupService.add(checkGroup, checkItemIds);
            // 新增成功
            return new Result(true, MessageConstant.ADD_CHECKGROUP_SUCCESS);
        } catch (Exception e) {
            // 新增失败
            return new Result(false, MessageConstant.ADD_CHECKGROUP_FAIL);
        }
    }
}
