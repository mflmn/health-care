package com.health.dao;

import com.health.pojo.OrderSetting;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface OrderSettingDao {
    List<OrderSetting> getOrderSettingByMonth(Map date);

    void add(OrderSetting orderSetting);

    void editNumberByOrderDate(OrderSetting orderSetting);

    long findCountByOrderDate(Date orderDate);
}
