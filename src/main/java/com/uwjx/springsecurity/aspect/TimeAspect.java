package com.uwjx.springsecurity.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class TimeAspect {

    @Around("execution(* com.uwjx.springsecurity.controller.AdminController.*(..))")
    public Object handle(ProceedingJoinPoint joinPoint) throws Throwable {
        log.warn("进入 TimeAspect");

        try {
            Object[] objects = joinPoint.getArgs();
            for (Object object : objects) {
                log.warn("obj->" , object.toString());
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        Object obj = joinPoint.proceed();
        log.warn("退出 TimeAspect");
        return obj;
    }
}
