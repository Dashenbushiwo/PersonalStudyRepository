package com.crud.service;

import com.crud.bean.Employee;
import com.crud.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> listEmployee() {
        return employeeMapper.selectWithAllList(null);
    }
}
