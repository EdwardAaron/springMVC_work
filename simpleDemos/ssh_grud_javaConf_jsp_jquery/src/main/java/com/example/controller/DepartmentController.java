package com.example.controller;

import com.example.bean.Department;
import com.example.bean.Msg;
import com.example.service.DepartmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * created by dfk
 * 2022/4/25
 */
@RestController
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    /*
    返回所有部门信息
     */
    @GetMapping("/depts")
    public Msg getDepts() {
        List<Department> departments = departmentService.getDepts();
        return Msg.success().add("deps", departments);
    }
}
