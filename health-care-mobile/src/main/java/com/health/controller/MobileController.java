package com.health.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.health.constant.MessageConstant;
import com.health.entity.Result;
import com.health.pojo.Order;
import com.health.service.IMobileService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@RestController
public class MobileController {
    @Reference(version = "1.0.0")
    private IMobileService iMobileService;

    @GetMapping("/order/findById.do")
    public Result findById(@RequestParam("orderId") Integer id) {
        try {
            Map map = iMobileService.findOrderById(id);
            // 查询预约信息成功
            return new Result(true, MessageConstant.QUERY_ORDER_SUCCESS, map);
        } catch (Exception e) {
            e.printStackTrace();
            // 查询预约信息失败
            return new Result(false, MessageConstant.QUERY_ORDER_FAIL);
        }
    }

    @PostMapping("/order/submit.do")
    public Result submitOrder(@RequestBody Map map) {
        // 调用体检预约服务
        try {
            map.put("orderType", Order.ORDERTYPE_WEIXIN);
            return iMobileService.order(map);
        } catch (Exception e) {
            e.printStackTrace();
            // 预约失败
            return new Result(false, MessageConstant.ORDERSETTING_FAIL);
        }
        // if (result.isFlag()) {
        //     // 预约成功, 发送短信通知
        //     String orderDate = (String) map.get("orderDate");
        // }
    }

    @GetMapping("/setmeal/findById.do")
    public Result findSetmealById(@RequestParam("setmealId") Integer setmealId) {
        try {
            return new Result(true, MessageConstant.QUERY_SETMEAL_SUCCESS, iMobileService.findSetmealById(setmealId));
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.QUERY_SETMEAL_FAIL);
    }

    @GetMapping("/setmeal/getAllSetmeal.do")
    public Result findAllSetmeal() {
        try {
            return new Result(true, MessageConstant.QUERY_SETMEALLIST_SUCCESS, iMobileService.findAllSetmeal());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new Result(false, MessageConstant.QUERY_CHECKITEM_FAIL);
    }
}
