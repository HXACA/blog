package com.xliu.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * @author liuxin
 * @version 1.0
 * @date 2020/3/6 16:37
 */
@Configuration
public class WebConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/admin/**")
                .excludePathPatterns("/admin/").excludePathPatterns("/admin/login").excludePathPatterns("/admin");
    }
}
