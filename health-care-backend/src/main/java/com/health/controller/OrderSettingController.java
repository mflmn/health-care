package com.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.constant.MessageConstant;
import com.health.entity.Result;
import com.health.pojo.OrderSetting;
import com.health.service.IOrderSettingService;
import com.health.utils.ApachePoiUtil;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RestController
@RequestMapping("/order-setting")
public class OrderSettingController {
    @Reference(version = "1.0.0")
    private IOrderSettingService orderSettingService;

    @PostMapping("/editNumberByDate.do")
    public Result editNumberByDate(@RequestBody OrderSetting orderSetting) {
        try {
            orderSettingService.editNumberByDate(orderSetting);
            // 预约设置成功
            return new Result(true, MessageConstant.ORDERSETTING_SUCCESS);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 预约设置失败
        return new Result(false, MessageConstant.ORDERSETTING_FAIL);
    }

    @GetMapping("/getOrderSettingByMonth.do")
    public Result getOrderSettingByMonth(@RequestParam("date") String date) {
        try {
            List<Map> list = orderSettingService.getOrderSettingByMonth(date);
            // 获取预约设置数据成功
            return new Result(true, MessageConstant.GET_ORDERSETTING_SUCCESS, list);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // 获取预约设置数据失败
        return new Result(false, MessageConstant.GET_ORDERSETTING_FAIL);
    }

    @PostMapping("/upload.do")
    public Result upload(@RequestParam("excelFile") MultipartFile excelFile) {
        try {
            // 读取Excel文件数据
            List<String[]> list = ApachePoiUtil.readExcel(excelFile);
            if (list.size() > 0) {
                List<OrderSetting> orderSettingList = new ArrayList<>();
                for (String[] strings : list) {
                    OrderSetting orderSetting = new OrderSetting(new Date(strings[0]), Integer.parseInt(strings[1]));
                    orderSettingList.add(orderSetting);
                }
                orderSettingService.add(orderSettingList);
            }

            return new Result(true, MessageConstant.IMPORT_ORDERSETTING_SUCCESS);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.IMPORT_ORDERSETTING_FAIL);
    }
}
