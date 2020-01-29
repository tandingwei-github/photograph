package com.tdw.photograph.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.config.annotation.*;

import java.nio.charset.Charset;

/**
 * @author 谭锭伟
 * @date 2019/12/24-14:11
 */


//@EnableWebMvc 这个注解意味着不需要springMVC的自动配置，
//              自动配置全部失效，所有配置都需要由开发者自己配置

//使用WebMvcConfigurer，重写方法实现对springmvc的扩展
@Configuration
//自动配置类
public class MyMvcConfig implements WebMvcConfigurer {

    @Autowired
    private MyInterceptor mvcInterceptor;


    @Override
    public void configureAsyncSupport(AsyncSupportConfigurer configurer) {
        //异步支持方法
    }

    @Override
    public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
        //相当于<mvc:Default-Servlet-Handler />
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //添加拦截器
        registry.addInterceptor(mvcInterceptor)
                .excludePathPatterns(
                        "/", "/index.html",
                        "/register", "/toRegister",
                        "/login", "/loginAdmin",
                        "/toLogin", "/toLoginAdmin",
                        "/resources/**", "/static/**",
                        "/webjars/**", "**/favicon.ico")
                .addPathPatterns("/**");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //静态资源注册
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("classpath:/resources/");
    }

    @Override
    //页面转发器
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("index");
        registry.addViewController("/index.html").setViewName("index");
    }

    //设置系统编码UTF-8
    @Bean
    public HttpMessageConverter<String> responseBodyConverter() {
        StringHttpMessageConverter converter =
                new StringHttpMessageConverter(Charset.forName("UTF-8"));
        return converter;
    }

    //配置国际化资源
    @Bean
    public LocaleResolver localeResolver() {
        return new MyLocaleResolver();
    }
}