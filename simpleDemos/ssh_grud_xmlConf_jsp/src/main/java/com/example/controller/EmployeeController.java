package com.example.controller;

import com.example.bean.Employee;
import com.example.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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
    public String getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {//pn=pageNumber
        PageHelper.startPage(pn,5);
        List<Employee> emps = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(emps,5);//连续显示页数
        model.addAttribute("pageInfo", pageInfo);
        return "list";
    }
}
