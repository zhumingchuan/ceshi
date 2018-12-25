package com.imooc.config;

import com.imooc.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
@Configuration
@Component
public class FilterConfig {
    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean registrationBean = new FilterRegistrationBean();
        MyFilter myFilter = new MyFilter();
        registrationBean.setFilter(myFilter);
        List<String> urlPatterns = new ArrayList<>();
        urlPatterns.add("/center/*");
        registrationBean.setUrlPatterns(urlPatterns);
        return registrationBean;
    }
}
