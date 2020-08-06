package com.uwjx.springsecurity.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
public class TestController {

    @RequestMapping(value = "a")
    public String a(){
        return "测试aaaaaaaaa";
    }

    @RequestMapping(value = "b")
    public String b(){
        return "测试bbbbbbbbb";
    }

    @RequestMapping(value = "c")
    public String c(){
        return "测试cccccccc";
    }
}
