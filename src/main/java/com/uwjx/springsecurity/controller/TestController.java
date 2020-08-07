package com.uwjx.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "test")
@Slf4j
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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        log.warn("principal -> {}" + authentication.getPrincipal().toString());

        return "测试cccccccc";
    }
}
