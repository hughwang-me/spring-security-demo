package com.uwjx.springsecurity;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringSecurityDemoApplication {

    /**
     * 默认情况下，Spring Boot UserDetailsServiceAutoConfiguration 自动化配置类，会创建一个内存级别的 InMemoryUserDetailsManager Bean 对象，提供认证的用户信息。
     * 这里，我们添加了 spring.security.user 配置项，UserDetailsServiceAutoConfiguration 会基于配置的信息创建一个用户 User 在内存中。
     * 如果，我们未添加 spring.security.user 配置项，UserDetailsServiceAutoConfiguration 会自动创建一个用户名为 "user" ，密码为 UUID 随机的用户 User 在内存中。
     */

    public static void main(String[] args) {
        SpringApplication.run(SpringSecurityDemoApplication.class, args);
    }

}
