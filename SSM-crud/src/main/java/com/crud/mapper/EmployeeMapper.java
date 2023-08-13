package com.crud.mapper;

import com.crud.bean.Employee;
import com.crud.bean.EmployeeExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface EmployeeMapper {
    long countByExample(EmployeeExample example);

    int deleteByExample(EmployeeExample example);

    int deleteByPrimaryKey(Integer empId);

    int insert(Employee row);

    int insertSelective(Employee row);

    List<Employee> selectByExample(EmployeeExample example);

    Employee selectByPrimaryKey(Integer empId);

    int updateByExampleSelective(@Param("row") Employee row, @Param("example") EmployeeExample example);

    int updateByExample(@Param("row") Employee row, @Param("example") EmployeeExample example);

    int updateByPrimaryKeySelective(Employee row);

    int updateByPrimaryKey(Employee row);

    List<Employee> selectWithAllList(Employee employee);
}