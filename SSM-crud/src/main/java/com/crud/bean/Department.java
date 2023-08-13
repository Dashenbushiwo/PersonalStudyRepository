package com.crud.bean;

import lombok.Data;

@Data
public class Department {
    private Integer deptId;

    /** 部门名称 */
    private String deptName;
}