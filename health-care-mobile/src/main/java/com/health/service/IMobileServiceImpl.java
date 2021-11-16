package com.health.service;

import com.alibaba.dubbo.config.annotation.Service;
import com.health.constant.MessageConstant;
import com.health.dao.IMobileDao;
import com.health.entity.Result;
import com.health.pojo.Member;
import com.health.pojo.Order;
import com.health.pojo.OrderSetting;
import com.health.pojo.Setmeal;
import com.health.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Transactional(rollbackFor = Exception.class)
@Service(interfaceClass = IMobileService.class, version = "1.0.0")
public class IMobileServiceImpl implements IMobileService {
    @Autowired
    private IMobileDao iMobileDao;

    @Override
    public Map findOrderById(Integer id) throws Exception {
        Map map = iMobileDao.findOrderById4Detail(id);
        if (map != null) {
            // 处理日期格式
            Date orderDate = (Date) map.get("orderDate");
            map.put("orderDate", DateUtils.parseDate2String(orderDate));
        }
        return map;
    }

    @Override
    public Result order(Map map) throws Exception {
        // 检查当前日期是否进行了预约设置
        String orderDate = (String) map.get("orderDate");
        Date date = DateUtils.parseString2Date(orderDate);
        OrderSetting orderSetting = iMobileDao.findOrderSettingByOrderDate(date);
        if (orderSetting == null) {
            return new Result(false, MessageConstant.SELECTED_DATE_CANNOT_ORDER);
        }

        // 检查预约日期是否预约已满
        // 可预约人数
        int number = orderSetting.getNumber();
        // 已预约人数
        int reservations = orderSetting.getReservations();
        if (reservations >= number) {
            //预约已满，不能预约
            return new Result(false, MessageConstant.ORDER_FULL);
        }

        // 检查当前用户是否为会员, 根据手机号判断
        String telephone = (String) map.get("telephone");
        Member member = iMobileDao.findMemberByTelephone(telephone);
        // 防止重复预约
        if (member != null) {
            Integer memberId = member.getId();
            int setmealId = Integer.parseInt((String) map.get("setmealId"));
            Order order = new Order(memberId, date, null, null, setmealId);
            List<Order> list = iMobileDao.findOrderByCondition(order);
            if (list != null && list.size() > 0) {
                // 已经完成了预约, 不能重复预约
                return new Result(false, MessageConstant.HAS_ORDERED);
            }
        }

        // 可以预约, 设置预约人数加一
        orderSetting.setReservations(orderSetting.getReservations() + 1);
        iMobileDao.editOrderSettingReservationsByOrderDate(orderSetting);

        if (member == null) {
            // 当前用户不是会员, 需要添加到会员表
            member = new Member();
            member.setName((String) map.get("name"));
            member.setPhoneNumber(telephone);
            member.setIdCard((String) map.get("idCard"));
            member.setSex((String) map.get("sex"));
            member.setRegTime(new Date());
            iMobileDao.addMember(member);
        }

        // 保存预约信息到预约表
        Order order = new Order(member.getId(), date, (String) map.get("orderType"), Order.ORDERSTATUS_NO, Integer.parseInt((String) map.get("setmealId")));
        iMobileDao.addOrder(order);

        return new Result(true, MessageConstant.ORDER_SUCCESS, order.getId());
    }

    @Override
    public Setmeal findSetmealById(Integer id) {
        return iMobileDao.findSetmealById(id);
    }

    @Override
    public List<Setmeal> findAllSetmeal() {
        return iMobileDao.findAllSetmeal();
    }
}
