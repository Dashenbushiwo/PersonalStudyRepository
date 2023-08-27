package com.ps.cloud.controller;

import com.ps.cloud.entity.Dept;
import com.ps.cloud.service.DeptClientService;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeptController_Consumer
{
    private final DeptClientService deptClientService;

    public DeptController_Consumer(DeptClientService deptClientService) {
        this.deptClientService = deptClientService;
    }

    @RequestMapping(value = "/consumer/dept/add")
    public boolean add(Dept dept)
    {
        return deptClientService.add(dept);
    }

    @RequestMapping(value = "/consumer/dept/get/{id}")
    public Dept get(@PathVariable("id") Long id)
    {
        return deptClientService.get(id);
    }

    @RequestMapping(value = "/consumer/dept/list")
    public List<Dept> list()
    {
        return deptClientService.list();
    }

}
