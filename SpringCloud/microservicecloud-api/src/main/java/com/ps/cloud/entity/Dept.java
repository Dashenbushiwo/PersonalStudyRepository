package com.ps.cloud.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class Dept implements Serializable {

    private Long deptno;
    private String dname;
    private String db_source;
}
