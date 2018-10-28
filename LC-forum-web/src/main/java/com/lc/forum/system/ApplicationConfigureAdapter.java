package com.lc.forum.system;


import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@Configuration
@EnableWebMvc
public class ApplicationConfigureAdapter implements WebMvcConfigurer {


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("swagger-ui.html").addResourceLocations("classpath:/META-INF/resources/");
        registry.addResourceHandler("/webjars/**").addResourceLocations("classpath:/META-INF/resources/webjars/");

    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    }

   /* @Bean
    public FilterRegistrationBean filterDemo4Registration() {
        FilterRegistrationBean registration = new FilterRegistrationBean();
       // 注入过滤器
        registration.setFilter(new RequestFilter());
        //拦截规则
        registration.addUrlPatterns("/");
        //过滤器名称
        registration.setName("requestFilter");
        //是否自动注册 false 取消Filter的自动注册
        registration.setEnabled(false);
        //过滤器顺序
        registration.setOrder(1);
        return registration;
    }*/

    /*@Bean(name = "requestFilter")
    public Filter requestFilter(){
        return new RequestFilter();
    }*/

}
