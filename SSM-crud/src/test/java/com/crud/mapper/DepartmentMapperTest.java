package com.crud.mapper;

import com.crud.bean.Department;
import com.crud.bean.DepartmentExample;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:application.xml")
public class DepartmentMapperTest {

    @Autowired
    private DepartmentMapper departmentMapper;

    @Test
    public void testInsert() {
        Department department = new Department();
        department.setDeptName("哦哈哟");
        departmentMapper.insertSelective(department);
        List<Department> departmentList = departmentMapper.selectByExample(new DepartmentExample());
        for (Department department1 : departmentList) {
            System.out.println(department1);
        }
    }
}
