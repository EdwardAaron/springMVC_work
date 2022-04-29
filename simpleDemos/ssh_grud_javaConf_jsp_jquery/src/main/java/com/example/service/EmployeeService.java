package com.example.service;

import com.example.bean.Employee;
import com.example.bean.EmployeeExample;
import com.example.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * created by dfk
 * 2022/4/24
 */
@Service
@Transactional
public class EmployeeService {
    @Autowired
    EmployeeMapper employeeMapper;

    public List<Employee> getAll() {
        EmployeeExample example = new EmployeeExample();
        example.setOrderByClause("emp_id asc");
        return employeeMapper.selectByExampleWithDept(example);
    }

    public void saveEmp(Employee employee) {
        employeeMapper.insertSelective(employee);
    }

    //校验用户名是否可用 true 当前用户名可用
    public boolean checkUserName(String username) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpNameEqualTo(username);
        long count = employeeMapper.countByExample(example);
        return count == 0;
    }

    public Employee getEmp(Integer id) {
        return employeeMapper.selectByPrimaryKey(id);
    }

    public void update(Employee employee) {
        employeeMapper.updateByPrimaryKeySelective(employee);
    }

    public void delteEmp(Integer id) {
        employeeMapper.deleteByPrimaryKey(id);
    }

    public void deleteBatch(List<Integer> ids) {
        EmployeeExample example = new EmployeeExample();
        EmployeeExample.Criteria criteria = example.createCriteria();
        criteria.andEmpIdIn(ids);
        employeeMapper.deleteByExample(example);
    }
}
