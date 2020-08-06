package com.uwjx.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "admin")
public class AdminController {

    @RequestMapping("/test")
    public String test(){
        return "测试";
    }
}
