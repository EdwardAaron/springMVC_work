package com.example.controller;

import com.example.pojo.Users;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * 接受 put和delete请求
 * created by dfk
 * 3/22/2022
 */
@Controller
public class PutAndDeleteController {
    //处理put请求
    @PutMapping("/putdel/")
    public String putRequest(Users user) {
        System.out.println(user);
        return "success";
    }
    //处理del请求
    @DeleteMapping("/putdel/")
    public String delRequest(Users user) {
        System.out.println(user);
        return "success";
    }
}
