package com.uwjx.springsecurity.configuration;

import com.uwjx.springsecurity.filter.JwtTokenFilter;
import com.uwjx.springsecurity.handler.AuthAccessDeniedHandler;
import com.uwjx.springsecurity.handler.LoginFailureHandler;
import com.uwjx.springsecurity.handler.LoginSuccessHandler;
import com.uwjx.springsecurity.service.AuthUserDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@Slf4j
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

//    @Resource
//    LoginSuccessHandler loginSuccessHandler;
//    @Resource
//    LoginFailureHandler loginFailureHandler;
//    @Resource
//    AuthAccessDeniedHandler authAccessDeniedHandler;
//    @Resource
//    JwtTokenFilter jwtTokenFilter;
//
//    @Resource
//    AuthUserDetailService authUserDetailService;

    @Bean
    BCryptPasswordEncoder encoder(){
        return new BCryptPasswordEncoder();
    }


//    @Override
//    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        log.warn("开始认证@@@@@@@@@@@@@@@@@@");
//        auth.userDetailsService(authUserDetailService).passwordEncoder(new BCryptPasswordEncoder());
////        auth.inMemoryAuthentication()
////                .passwordEncoder(NoOpPasswordEncoder.getInstance())
////                .withUser("admin")
////                .password("123456")
////                .roles("ADMIN")
////                .and()
////                .withUser("wanghuan")
////                .password("123456")
////                .roles("NORMAL");
//    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/req/**").permitAll()
//                .antMatchers("/code").permitAll()
                .antMatchers("/test/a").hasRole("USER")
                .antMatchers("/test/b").hasRole("ADMIN")
                .antMatchers("/test/c").access("hasRole('ROLE_NORMAL')")


                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .loginPage("/static/login.html")
//                    .successHandler(loginSuccessHandler)
//                    .failureHandler(loginFailureHandler)
                    .permitAll();
//                .and()
//                    .logout()
//                    .permitAll()
//        ;
//
////        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);//禁用Session
//        http.csrf().disable();
//        http.exceptionHandling().accessDeniedHandler(authAccessDeniedHandler);
//
//        http.addFilterBefore(jwtTokenFilter , UsernamePasswordAuthenticationFilter.class);

    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.
//    }
}
