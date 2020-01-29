package com.tdw.photograph;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

//主程序类，主入口类
@MapperScan("com.tdw.photograph.mapper")
@SpringBootApplication
@ServletComponentScan
//自动扫描带有(@WebServlet, @WebFilter, @WebListener)注解的类，完成注册。
public class MainClass {
    public static void main(String[] args) {
        SpringApplication.run(MainClass.class, args);
    }
}