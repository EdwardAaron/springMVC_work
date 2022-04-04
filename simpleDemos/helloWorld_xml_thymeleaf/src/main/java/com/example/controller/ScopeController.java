package com.example.controller;

import com.example.pojo.Employee;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Map;


/**
 * 设置域共享
 * created by dfk
 * 3/21/2022
 */
@Controller
@RequestMapping("/scope/")
public class ScopeController {
    @GetMapping("/servletapi")
    public String servletApi(HttpServletRequest request) {
        request.setAttribute("result", "use servletApi");
        return "scope_success";
    }

    //通过ModelAndView设置域数据
    //scope为request
    @GetMapping("/modelandview")
    public ModelAndView modelAndView() {
        ModelAndView modelAndView = new ModelAndView();
        ArrayList<Employee> employees = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            Employee emp = new Employee();
            emp.setId(i);
            emp.setLastName("emp :" + i);
            emp.setGender("mail");
            emp.setEmail("qq.com");
            employees.add(emp);
        }
        modelAndView.addObject("employees", employees);
        modelAndView.setViewName("employee_list");
        return modelAndView;
    }

    //通过Model设置域数据
    //scope为request
    @GetMapping("/model")
    public String model(Model model) {
        model.addAttribute("result", "use model");
        return "scope_success";
    }

    //通过map设置域数据
    //scope为request
    @GetMapping("/map")
    public String map(Map<String,Object> map) {
        map.put("result", "use map");
        return "scope_success";
    }
    //通过ModelMap设置域数据
    //scope为request
    @GetMapping("/modelmap")
    public String modelMap(ModelMap modelMap) {
        modelMap.addAttribute("result", "use modelMap");
        return "scope_success";
    }

    //scope session
    @GetMapping("/seservletapi")
    public String seServletapi(HttpSession session) {
        session.setAttribute("seResult", "use servletApi Session");
        return "scope_success";
    }

    //scope application
    @GetMapping("/appservletapi")
    public String appServletapi(HttpSession session) {
        ServletContext servletContext = session.getServletContext();
        servletContext.setAttribute("seResult", "use servletApi scope:application");
        return "scope_success";
    }

}
