package com.uwjx.springsecurity.controller;

import com.uwjx.springsecurity.dao.UserMapper;
import com.uwjx.springsecurity.domain.entity.UserEntity;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "admin")
@Slf4j
public class AdminController {

    @Resource
    UserMapper userMapper;

    @RequestMapping("/user")
    public String test(){

        List<UserEntity> users = userMapper.selectAll();
        users.forEach(user -> {
            log.warn("user-> {}" , user.toString());
        });
        return "测试";
    }
}
