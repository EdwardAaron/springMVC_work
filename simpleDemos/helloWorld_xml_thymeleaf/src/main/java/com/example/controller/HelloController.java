package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by dfk
 * 3/20/2022
 */
@Controller
public class HelloController {
    // /->/WEB-INF/templates/index.html
    @RequestMapping("/")
    public String index() {
        return "index";
    }
    @RequestMapping("/success")
    public String toTarget() {
        return "success";
    }
}
