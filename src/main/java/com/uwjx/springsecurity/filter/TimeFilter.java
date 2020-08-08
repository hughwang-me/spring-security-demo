package com.uwjx.springsecurity.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;
import java.util.Date;

@Component
@Slf4j
public class TimeFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.warn("初始化TimeFilter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        log.warn("doFilter - TimeFilter 开始: {}" , new Date().toString());
        filterChain.doFilter(servletRequest , servletResponse);
        log.warn("doFilter - TimeFilter 结束: {}" , new Date().toString());
    }

    @Override
    public void destroy() {
        log.warn("destroy TimeFilter");
    }
}
