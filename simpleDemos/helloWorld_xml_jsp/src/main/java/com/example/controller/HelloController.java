package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * created by dfk
 * 3/21/2022
 */
@Controller
public class HelloController {
    @GetMapping("/success")
    public String success() {
        System.out.println("success");
        return "success";
    }

    @GetMapping("/forward")
    public String forward() {
        return "forward:/success";
    }

    @GetMapping("/redirect")
    public String redirect() {
        return "redirect:/success";
    }
}
