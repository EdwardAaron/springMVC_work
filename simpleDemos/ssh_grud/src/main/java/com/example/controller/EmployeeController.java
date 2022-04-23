package com.example.controller;

import com.example.bean.Employee;
import com.example.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * created by dfk
 * 2022/4/23
 */
@Controller
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;

    /**
     * 分页查询
     */
    @GetMapping("/emps")
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pg) {//pg=pageNumber
        List<Employee> emps = employeeService.getAll();
        return "list";
    }
}
