package com.health.pojo;

import java.io.Serializable;
import java.util.List;

/**
 * 体检套餐
 *
 * @author yuelimin
 */
public class Setmeal implements Serializable {
    private static final long serialVersionUID = 4875772601103951502L;

    private Integer id;
    private String name;
    private String code;
    private String helpCode;
    private String sex;//套餐适用性别: 0不限 1男 2女
    private String age;//套餐适用年龄
    private Float price;//套餐价格
    private String remark;
    private String attention;
    private String img;//套餐对应图片存储路径
    private List<CheckGroup> checkGroups;//体检套餐对应的检查组, 多对多关系
}