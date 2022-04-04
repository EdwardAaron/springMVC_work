package com.example.controller;

import com.example.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;

/**
 * 获取参数
 * created by dfk
 * 3/20/2022
 */
@Controller
@RequestMapping("/param")
public class ParamController {
    //可以从request中获取参数
    @RequestMapping("/servletapi")
    public String servletApi(HttpServletRequest request) {
        String name = request.getParameter("name");
        String age = request.getParameter("age");
        System.out.println("name:" + name);
        System.out.println("age:" + age);
        return "success";
    }

    /**
     * 当参数形参和url中的参数名相同时，会自动匹配
     * 如果参数有多个值，形参可以是一个字符串数组，或单个字符串
     * 单个字符串时，形参的值以逗号分割
     */
    @GetMapping("/automatch")
    public String autoMatch(String name, String[] hobby) {//hobby 可以是单个字符串
        System.out.println("name:" + name);
        System.out.println("hobby:" + Arrays.toString(hobby));
        return "success";
    }

    //@RequestParam 从指定的请求参数获取值
    @GetMapping("/auto_not_match")
    public String auto_not_match(@RequestParam("username") String name, String[] hobby) {//hobby 可以是单个字符串
        System.out.println("name:" + name);
        System.out.println("hobby:" + Arrays.toString(hobby));
        return "success";
    }

    /**
     * @RequestHeader 获取请求头信息
     */
    @GetMapping("/getRequestHeader")
    public String getRequestHeader(@RequestHeader("host") String host) {
        System.out.println("host:" + host);
        return "success";
    }

    /**
     * @CookieValue 获取cookie
     */
    @GetMapping("/getCookie")
    public String getCookie(@CookieValue(value = "JSESSIONID", required = false) String JSESSION, HttpServletRequest request) {
        //获取session以便响应中返回 JSESSIONID cookie，下次调用的时候请求中会有 JSESSIONID cookie
        request.getSession();
        System.out.println("JSESSIONID:" + JSESSION);
        return "success";
    }

    /**
     * 请求参数和pojo属性自动映射
     */
    @GetMapping("/getPojo")
    public String getPojo(Users user){
        System.out.println("user:" + user);
        return "success";
    }

    /**
     * 需要配置CharacterEncodingFilter解决乱码
     */
    @PostMapping("/postPojo")
    public String postPojo(Users user){
        System.out.println("user:" + user);
        return "success";
    }
}
