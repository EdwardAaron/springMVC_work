package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * created by dfk
 * 4/4/2022
 */
@Controller
@RequestMapping("/ari")
public class ThrowExceptionController {
    @GetMapping("/handarithmetic")
    public String handArithmetic() {
        //SimpleMappingExceptionResolver处理
        throw new ArithmeticException("算数异常");
    }

    @GetMapping("/handnullpointexception")
    public String handNullPointException() {
        //SimpleMappingExceptionResolver处理
        throw new NullPointerException("空指针异常");
    }
}
