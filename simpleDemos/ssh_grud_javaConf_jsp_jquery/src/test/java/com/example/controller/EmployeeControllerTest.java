package com.example.controller;

import com.example.bean.Employee;
import com.github.pagehelper.PageInfo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.util.List;

/**
 * created by dfk
 * 2022/4/24
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:springMVC.xml"})
public class EmployeeControllerTest {
    @Autowired
    WebApplicationContext context;
    MockMvc mockMvc;

    @Before
    public void initMockMVC() {
        mockMvc = MockMvcBuilders.webAppContextSetup(context).build();
    }

    @Test
    public void getEmpsTest() throws Exception {
        MvcResult mvcResult = mockMvc.perform(
                MockMvcRequestBuilders
                        .get("/emps")
                        .param("pn", "5"))
                .andReturn();
        MockHttpServletRequest request = mvcResult.getRequest();
        PageInfo pageInfo = (PageInfo) request.getAttribute("pageInfo");
        System.out.println("当前页码:" + pageInfo.getPageNum());
        System.out.println("总页码:" + pageInfo.getPages());
        System.out.println("总记录数:" + pageInfo.getTotal());
        for (int i : pageInfo.getNavigatepageNums()) {
            System.out.print(" "+i);
        }

        //员工
        List<Employee> list = pageInfo.getList();
        for (Employee emp : list) {
            System.out.println("ID:"+emp.getEmpId()+" name:"+emp.getEmpName());
        }
    }
}
