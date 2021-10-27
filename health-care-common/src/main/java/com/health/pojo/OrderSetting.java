package com.health.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * 预约设置
 *
 * @author yuelimin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderSetting implements Serializable {
    private static final long serialVersionUID = 5149217995349205977L;

    private Integer id;
    private Date orderDate;//预约设置日期
    private int number;//可预约人数
    private int reservations;//已预约人数
}
