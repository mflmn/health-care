package com.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.constant.MessageConstant;
import com.health.entity.PageResult;
import com.health.entity.QueryPageBean;
import com.health.entity.Result;
import com.health.pojo.Setmeal;
import com.health.service.IFileService;
import com.health.service.ISetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * @author YueLiMin
 * @version 1.0.0
 * @since 11
 */
@RestController
@RequestMapping("/setmeal")
public class SetmealController {
    @Reference(version = "1.0.0")
    private ISetmealService iSetmealService;
    @Autowired
    private IFileService iFileService;

    @GetMapping("/findById.do")
    public Result queryById(@RequestParam("setmealId") Integer setmealId) {
        try {
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, iSetmealService.queryById(setmealId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
    }

    @PutMapping("/edit.do")
    public Result updateById(@RequestBody Setmeal setmeal) {
        try {
            iSetmealService.edit(setmeal);
            return new Result(true, "套餐修改成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, "套餐修改失败");
    }

    @DeleteMapping("/delete.do")
    public Result deleteById(@RequestParam("setmealId") Integer setmealId) {
        try {
            iSetmealService.delete(setmealId);
            return new Result(true, "删除套餐成功");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, "删除套餐失败");
    }

    @PostMapping("/findPage.do")
    public PageResult findPage(@RequestBody QueryPageBean queryPageBean) {
        return iSetmealService.pageQuery(
                queryPageBean.getCurrentPage(),
                queryPageBean.getPageSize(),
                queryPageBean.getQueryString()
        );
    }

    @PostMapping("/upload.do")
    public Result uploadFile(@RequestParam(value = "file") MultipartFile file) throws IOException {
        try {
            return new Result(true, MessageConstant.PIC_UPLOAD_SUCCESS, iFileService.imageUpload(file.getOriginalFilename(), file.getBytes()));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.PIC_UPLOAD_FAIL);
    }

    @PostMapping("/add.do")
    public Result add(@RequestBody Setmeal setmeal, @RequestParam("checkGroupIds") Integer[] checkGroupIds) {
        try {
            iSetmealService.add(setmeal, checkGroupIds);
            return new Result(true, MessageConstant.ADD_SETMEAL_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.ADD_SETMEAL_FAIL);
    }
}
