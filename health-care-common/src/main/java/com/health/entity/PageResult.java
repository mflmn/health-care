package com.health.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author yuelimin
 * @version 1.0.0
 * @since 11
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageResult implements Serializable {
    private static final long serialVersionUID = 3318200036147778431L;

    private Long total;//总记录数
    private List<Object> rows;//当前页结果
}
