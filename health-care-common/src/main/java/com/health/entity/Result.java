package com.health.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result implements Serializable {
    private static final long serialVersionUID = -8798088536669757141L;

    private boolean flag;//执行结果, true为执行成功 false为执行失败
    private String message;//返回提示信息, 主要用于页面提示信息
    private Object data;//返回数据

    public Result(boolean flag, String message) {
        this.flag = flag;
        this.message = message;
    }
}
