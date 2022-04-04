package com.example.controller;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * created by dfk
 * 4/4/2022
 */
@ControllerAdvice
public class MyExceptionHandler {
    @ExceptionHandler({NullPointerException.class})
    public String handException(Exception ex, Model model) {
        model.addAttribute("ex", ex);
        return "error";
    }
}
