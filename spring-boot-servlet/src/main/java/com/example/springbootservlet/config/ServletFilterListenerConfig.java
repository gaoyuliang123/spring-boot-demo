package com.example.springbootservlet.config;

import com.example.springbootservlet.filter.CustomFilter;
import com.example.springbootservlet.listener.CustomListenner;
import com.example.springbootservlet.servlet.CustomServlet;
import com.sun.org.apache.bcel.internal.generic.RETURN;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * @description:
 * @author: TGL
 * @date: 2018/6/24 22:37
 */
@Configuration
public class ServletFilterListenerConfig {
    @Bean
    public ServletRegistrationBean servletRegistrationBean() {
        ServletRegistrationBean registrationBean = new ServletRegistrationBean(new CustomServlet(), "/servlet");
        return registrationBean;
    }

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        registrationBean.setFilter(new CustomFilter());
        registrationBean.setUrlPatterns(Arrays.asList("/hello", "/servlet"));
        return registrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean servletListenerRegistrationBean() {
        return new ServletListenerRegistrationBean(new CustomListenner());
    }
}
