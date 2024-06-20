package com.example.demo.cors;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

public class webConfig {
    @Bean
    public FilterRegistrationBean<CorsFilter> corsFilterRegistrationBean() {
        FilterRegistrationBean<CorsFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new CorsFilter());
        registrationBean.addUrlPatterns("/*"); // Apply the filter to all URL patterns
        registrationBean.setName("CorsFilter");
        registrationBean.setOrder(1); // Set the order of the filter
        return registrationBean;
    }
}
