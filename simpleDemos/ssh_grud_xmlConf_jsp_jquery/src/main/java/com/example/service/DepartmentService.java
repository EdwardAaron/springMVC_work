package com.example.service;

import com.example.bean.Department;
import com.example.dao.DepartmentMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by dfk
 * 2022/4/25
 */
@Service
public class DepartmentService {
    @Autowired
    private DepartmentMapper departmentMapper;
    public List<Department> getDepts() {
        return departmentMapper.selectByExample(null);
    }
}
