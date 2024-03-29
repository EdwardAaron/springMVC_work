package com.example.service;

import com.example.bean.Employee;
import com.example.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * created by dfk
 * 2022/4/24
 */
@SuppressWarnings("SpringJavaAutowiredFieldsWarningInspection")
@Service
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        return employeeMapper.selectByExampleWithDept(null);
    }
}
