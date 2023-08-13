package com.crud.mapper;

import com.crud.bean.Employee;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class EmployeeMapperTest {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Test
    public void testInsert() {
        Employee employee = new Employee();
        employee.setEmpName("米哈游");
        employee.setGender("M");
        employee.setEmail("1276063856@qq.com");
        employee.setDeptId(4);
        employeeMapper.insertSelective(employee);
        List<Employee> employeeList = employeeMapper.selectByExample(null);
        for (Employee item : employeeList) {
            System.out.println(item);
        }
    }

    @Test
    public void testSelect() {
        List<Employee> employeeList = employeeMapper.selectWithAllList(null);
        for (Employee employee : employeeList) {
            System.out.println(employee);
        }
    }
}
