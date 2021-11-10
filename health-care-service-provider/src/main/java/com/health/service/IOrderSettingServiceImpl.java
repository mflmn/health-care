package com.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.dao.OrderSettingDao;
import com.health.pojo.OrderSetting;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Transactional(rollbackFor = Exception.class)
@Service(interfaceClass = IOrderSettingService.class, version = "1.0.0")
public class IOrderSettingServiceImpl implements IOrderSettingService {
    @Autowired
    private OrderSettingDao orderSettingDao;

    @Override
    public void editNumberByDate(OrderSetting orderSetting) {
        long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
        if(count > 0){
            // 当前日期已经进行了预约设置, 需要进行修改操作
            orderSettingDao.editNumberByOrderDate(orderSetting);
        }else{
            // 当前日期没有进行预约设置, 进行添加操作
            orderSettingDao.add(orderSetting);
        }
    }

    @Override
    public List<Map> getOrderSettingByMonth(String date) {
        // 2019-3-1
        String dateBegin = date + "-1";
        // 2019-3-31
        String dateEnd = date + "-31";
        Map map = new HashMap();
        map.put("dateBegin", dateBegin);
        map.put("dateEnd", dateEnd);
        List<OrderSetting> list = orderSettingDao.getOrderSettingByMonth(map);
        List<Map> data = new ArrayList<>();
        for (OrderSetting orderSetting : list) {
            Map orderSettingMap = new HashMap();
            // 获得日期(几号)
            orderSettingMap.put("date", orderSetting.getOrderDate().getDate());
            // 可预约人数
            orderSettingMap.put("number", orderSetting.getNumber());
            // 已预约人数
            orderSettingMap.put("reservations", orderSetting.getReservations());
            data.add(orderSettingMap);
        }
        return data;
    }

    @Override
    public void add(List<OrderSetting> list) {
        if (list != null && list.size() > 0) {
            for (OrderSetting orderSetting : list) {
                // 检查此数据(日期)是否存在
                long count = orderSettingDao.findCountByOrderDate(orderSetting.getOrderDate());
                if (count > 0) {
                    // 已经存在, 执行更新操作
                    orderSettingDao.editNumberByOrderDate(orderSetting);
                } else {
                    // 不存在, 执行添加操作
                    orderSettingDao.add(orderSetting);
                }
            }
        }
    }
}
