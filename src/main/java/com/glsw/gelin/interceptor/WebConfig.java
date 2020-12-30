package com.glsw.gelin.interceptor;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @program: gelin
 * @description:控制器过滤
 * @author: 作者
 * @create: 2020-12-10 12:28
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/admin/**")
                .excludePathPatterns("/admin")
                .excludePathPatterns("/admin/login")
                .excludePathPatterns("/admin/uppswd")
                .excludePathPatterns("/admin/cards")
                .excludePathPatterns("/admin/upuserpassword")
                .excludePathPatterns("/admin/logout")
                .excludePathPatterns("/admin/register");

    }
}
