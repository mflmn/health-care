package com.health.service;

import com.health.pojo.OrderSetting;

import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IOrderSettingService {
    /**
     * 根据日期修改可预约人数
     *
     * @param orderSetting
     */
    void editNumberByDate(OrderSetting orderSetting);

    /**
     * 根据日期查询预约设置数据
     *
     * @param date 2019-03
     * @return 预约设置数据
     */
    List<Map> getOrderSettingByMonth(String date);

    void add(List<OrderSetting> list);
}
