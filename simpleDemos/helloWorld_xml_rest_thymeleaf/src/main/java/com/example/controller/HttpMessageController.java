package com.example.controller;

import com.example.pojo.Users;
import org.springframework.http.RequestEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * 转换请求体、返回体
 * created by dfk
 * 3/22/2022
 */
@Controller
@RequestMapping("/httpmes/")
public class HttpMessageController {
    //requestBody 请求体
    @PostMapping("/str")
    public String requsetBodyStr(@RequestBody String requestBody) {
        System.out.println("requsetBody:" + requestBody);
        return "success";
    }

    //requestEntity 整个请求
    @PostMapping("/requestentity")
    public String requestEntity(RequestEntity<String> requestEntity) {
        System.out.println("requestEntity headers:" + requestEntity.getHeaders());
        System.out.println("requestEntity body:" + requestEntity.getBody());
        return "success";
    }

    @GetMapping("/servletaip")
    public void servletaip(HttpServletResponse response) throws IOException {
        response.getWriter().println("hello lucia");
    }

    //@ResponseBody 作为请求体返回
    @GetMapping("/respondbody")
    @ResponseBody
    public String respondbody() {
        return "hello lucia";
    }

    //返回客户端一个对象，需要json实现
    @GetMapping("/respondbodypojo")
    @ResponseBody
    public Users respondbodypojo() {
        Users users = new Users();
        users.setUsername("lucia");
        users.setAge(19);
        return users;
    }

    @PostMapping("/ajaxPost")
    @ResponseBody
    public Users ajaxPost(String hello) {
        Users users = new Users();
        users.setUsername("lucia");
        users.setAge(19);
        users.setMessage(StringUtils.hasLength(hello) ?
                hello.toUpperCase(Locale.ROOT)
                : "HELLO WORLD");
        return users;
    }

}
