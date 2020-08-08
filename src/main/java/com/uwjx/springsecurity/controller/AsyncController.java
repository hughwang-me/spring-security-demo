package com.uwjx.springsecurity.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.concurrent.Callable;

@RestController
@Slf4j
@RequestMapping(value = "req")
public class AsyncController {

    @GetMapping(value = "sync")
    public String sync(){
        log.warn("同步请求开始: {}" , new Date().toString());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.warn("同步请求开始: {}" , new Date().toString());
        return "success sync";
    }

    @GetMapping(value = "async")
    public Callable<String> async(){
        log.warn("异步请求开始: {}" , new Date().toString());
        Callable<String> resp = new Callable<String>() {
            @Override
            public String call() throws Exception {
                log.warn("异步请求 副线程开始: {}" , new Date().toString());
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                log.warn("异步请求 副线程结束: {}" , new Date().toString());
                return "success async";
            }
        };
        log.warn("异步请求结束: {}" , new Date().toString());
        return resp;
    }
}
