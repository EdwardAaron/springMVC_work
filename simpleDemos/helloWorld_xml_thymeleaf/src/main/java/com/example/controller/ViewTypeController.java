package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by dfk
 * 3/21/2022
 */
@Controller
@RequestMapping("/view/")
public class ViewTypeController {
    @GetMapping("/success")
    public String success() {
        return "success";
    }
    @GetMapping("/forward")
    public String forward() {
        System.out.println("forward ....");
        return "forward:/view/success";
    }

    @GetMapping("/redirect")
    public String redirect() {
        System.out.println("redirect ...");
        return "redirect:/view/success";
    }
}
