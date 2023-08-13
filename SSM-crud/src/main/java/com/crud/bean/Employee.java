package com.crud.bean;

import lombok.Data;

@Data
public class Employee {
    private Integer empId;

    /** 员工名称 */
    private String empName;

    /** 性别：M：男 W：女 */
    private String gender;

    /** 电子邮箱 */
    private String email;

    /** 部门主键 */
    private Integer deptId;

    /** 所属部门 */
    private Department department;
}