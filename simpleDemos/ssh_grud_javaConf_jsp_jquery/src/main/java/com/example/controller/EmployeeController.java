package com.example.controller;

import com.example.bean.Employee;
import com.example.bean.Msg;
import com.example.service.EmployeeService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * created by dfk
 * 2022/4/23
 */
@RestController
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @GetMapping("/checkempname")
    public Msg checkEmpName(String empName) {
        String regex = "^([a-zA-Z0-9_-]{5,16})|([\\u2E80-\\u9FFF]{2,16})$";
        if (!empName.matches(regex)) {
            return Msg.fail().add("val_msg", "用户名可以是2-16位中文，或6-16英文数字!");
        }
        boolean re = employeeService.checkUserName(empName);
        if (re) {
            return Msg.success();
        } else {
            return Msg.fail().add("val_msg", "用户名不可用");
        }
    }

    /**
     * 分页查询
     */
    @GetMapping("/emps")
    public Msg getEmps(@RequestParam(value = "pn", defaultValue = "1") Integer pn, Model model) {//pn=pageNumber
        System.out.println("++++++++++++++");
        PageHelper.startPage(pn, 5);
        List<Employee> emps = employeeService.getAll();
        PageInfo pageInfo = new PageInfo(emps, 5);//连续显示页数
        return Msg.success().add("pageInfo", pageInfo);
    }

    //tomcat 不会封装 put请求体
    @PutMapping("/emp/{empId}")
    public Msg saveEmp(Employee employee) {
        employeeService.update(employee);
        return Msg.success();
    }

    @PostMapping("/emp")
    public Msg saveEmp(@Valid Employee employee, BindingResult result) {
        if (result.hasErrors()) {
            List<FieldError> errors = result.getFieldErrors();
            HashMap<String, Object> map = new HashMap<>();
            for (FieldError error : errors) {
                map.put(error.getField(), error.getDefaultMessage());
            }
            return Msg.fail().add("errorFields", map);
        } else {
            employeeService.saveEmp(employee);
            return Msg.success();
        }
    }

    @GetMapping("/emp/{id}")
    public Msg getEmp(@PathVariable Integer id) {
        Employee employee = employeeService.getEmp(id);
        return Msg.success().add("emp", employee);
    }

    /**
     * 支持批量删除 1,2,3
     *
     * @param ids
     * @return
     */
    @DeleteMapping("/emp/{ids}")
    public Msg delteEmps(@PathVariable String ids) {
        //批量删除
        if (ids.contains(",")) {
            List<Integer> del_ids = new ArrayList<>();
            for (String sid : ids.split(",")) {
                del_ids.add(Integer.parseInt(sid));
            }
            employeeService.deleteBatch(del_ids);
            //单个删除
        } else {
            employeeService.delteEmp(Integer.parseInt(ids));
        }
        return Msg.success().setMsg("删除成功");
    }
}
