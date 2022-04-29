package com.example.dao;

import com.example.bean.Department;
import com.example.conf.ApplicationConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * created by dfk
 * 2022/4/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
public class DepartmentMapperTest {
    @Autowired
    DepartmentMapper departmentMapper;
    @Test
    public void insertSelectiveTest() {
        departmentMapper.insertSelective(new Department(null, "开发部"));
        departmentMapper.insertSelective(new Department(null, "测试部"));
    }
}
