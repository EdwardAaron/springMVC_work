package com.example.dao;

import com.example.bean.Department;
import com.example.bean.Employee;
import com.example.dao.DepartmentMapper;
import com.example.dao.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

/**
 * created by dfk
 * 2022/4/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class EmployeeMapperTest {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    SqlSessionTemplate sqlSessionTemplate;

    @Test
    public void insertSelectiveTest() {
        //使用spring单元测试来代替
//        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
//        context.getBean(DepartmentMapper.class);
        //部门插入
        EmployeeMapper mapper = sqlSessionTemplate.getMapper(EmployeeMapper.class);
        for (int i = 0; i < 1000; i++) {
            String uid = UUID.randomUUID().toString().substring(0, 5) + i;
            mapper.insertSelective(
                    new Employee(null, uid, "M", uid + "123456@qq.com", 1));
        }
    }
}
