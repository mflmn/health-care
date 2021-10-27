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
public class QueryPageBean implements Serializable {
    private static final long serialVersionUID = 632813100658432109L;

    private Integer currentPage;//页码
    private Integer pageSize;//每页记录数
    private String queryString;//查询条件
}
