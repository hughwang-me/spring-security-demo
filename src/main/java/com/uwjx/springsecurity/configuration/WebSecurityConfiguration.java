package com.uwjx.springsecurity.configuration;

import com.uwjx.springsecurity.service.AuthUserDetailService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;

import javax.annotation.Resource;

@Configuration
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Resource
    AuthUserDetailService authUserDetailService;


    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(authUserDetailService).passwordEncoder(NoOpPasswordEncoder.getInstance());
//        auth.inMemoryAuthentication()
//                .passwordEncoder(NoOpPasswordEncoder.getInstance())
//                .withUser("admin")
//                .password("123456")
//                .roles("ADMIN")
//                .and()
//                .withUser("wanghuan")
//                .password("123456")
//                .roles("NORMAL");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/actuator/**").permitAll()
                .antMatchers("/admin/**").permitAll()
                .antMatchers("/test/a").permitAll()
                .antMatchers("/test/b").hasRole("ADMIN")
                .antMatchers("/test/c").access("hasRole('ROLE_NORMAL')")
                .anyRequest().authenticated()
                .and()
                    .formLogin()
                    .permitAll()
                .and()
                    .logout()
                    .permitAll()
        ;

        http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }

//    @Override
//    public void configure(WebSecurity web) throws Exception {
//        web.
//    }
}
