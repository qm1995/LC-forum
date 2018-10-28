package com.lc.forum.system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author qiumin
 * @create 2018/10/28 20:37
 * @desc
 **/
@SpringBootApplication
@ComponentScan("com.lc.forum.*")
@EnableTransactionManagement
@MapperScan(basePackages = "com.lc.forum.system.*")
public class LCForumApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        builder.sources(this.getClass());
        return super.configure(builder);
    }
    public static void main(String[] args) {
        SpringApplication.run(LCForumApplication.class, args);
    }
}
