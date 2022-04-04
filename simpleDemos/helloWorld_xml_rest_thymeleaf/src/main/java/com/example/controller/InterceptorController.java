package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by dfk
 * 3/23/2022
 */
@Controller
@RequestMapping("interceptor")
public class InterceptorController {
    @GetMapping("/hello")
    public String hello() {
        System.out.println("InterceptorController hello");
        return "success";
    }
}
