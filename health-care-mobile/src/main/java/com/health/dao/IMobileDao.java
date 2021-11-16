package com.health.dao;

import com.github.pagehelper.Page;
import com.health.pojo.*;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
public interface IMobileDao {
    // member on
    List<Member> findAllMember();

    Page<Member> selectMemberByCondition(String queryString);

    void addMember(Member member);

    void deleteMemberById(Integer id);

    Member findMemberById(Integer id);

    Member findMemberByTelephone(String telephone);

    void editMember(Member member);

    Integer findMemberCountBeforeDate(String date);

    Integer findMemberCountByDate(String date);

    Integer findMemberCountAfterDate(String date);

    Integer findMemberTotalCount();
    // member end

    // order on
    Map findOrderById4Detail(Integer id);

    void addOrder(Order order);

    List<Order> findOrderByCondition(Order order);
    // order end

    // order setting on
    void editOrderSettingReservationsByOrderDate(OrderSetting orderSetting);

    OrderSetting findOrderSettingByOrderDate(Date orderDate);
    // order setting end

    // setmeal on
    List<CheckItem> findCheckItemByCheckGroupId(Integer checkGroupId);

    List<CheckGroup> findCheckGroupBySetmealId(Integer setmealId);

    Setmeal findSetmealById(Integer id);

    List<Setmeal> findAllSetmeal();
    // setmeal end
}
