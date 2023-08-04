package com.dyingzhang.auth.config;

import com.dyingzhang.auth.filter.MyFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * @author DyingZhang
 * @Create 2023-08-03 下午 4:55
 * @Discription
 */
@Configuration
public class FilterConfig {
    @Resource
    public MyFilter myFilter;

    @Bean
    public FilterRegistrationBean filterRegistrationBean() {
        FilterRegistrationBean<MyFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(myFilter);
        registrationBean.addUrlPatterns("/data");
        registrationBean.setName("filter1");
        registrationBean.setOrder(1);
        return registrationBean;
    }

}
