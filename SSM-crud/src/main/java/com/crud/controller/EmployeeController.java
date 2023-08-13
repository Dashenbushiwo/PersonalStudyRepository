package com.crud.controller;

import com.crud.bean.Employee;
import com.crud.common.Result;
import com.crud.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/employee/")
public class EmployeeController {
    
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping("page")
    public String getPage(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                             @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,
                             Model model) {
        PageHelper.startPage(pageNum, pageSize);
        List<Employee> employeeList = employeeService.listEmployee();
        model.addAttribute("pageInfo", new PageInfo<>(employeeList, 5));
        return "employee/page";
    }

    @RequestMapping("pageJson")
    @ResponseBody
    public Result getPageJson(@RequestParam(value = "pageNum", defaultValue = "1") Integer pageNum,
                          @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        return Result.of(new PageInfo<>(employeeService.listEmployee(), 5));
    }
}
