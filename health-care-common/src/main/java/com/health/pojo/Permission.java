package com.health.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 * 权限
 *
 * @author yuelimin
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {
    private static final long serialVersionUID = 5972178241787954935L;

    private Integer id;
    private String name; // 权限名称
    private String keyword; // 权限关键字, 用于权限控制
    private String description; // 描述
    private Set<Role> roles = new HashSet<Role>(0);
}
