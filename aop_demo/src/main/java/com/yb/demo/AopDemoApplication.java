package com.yb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * @author yb
 * @description
 * @data 2021/6/3
 */
@SpringBootApplication
@EnableAspectJAutoProxy
public class AopDemoApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(AopDemoApplication.class);
        Object testController = context.getBean("testController");
        System.out.println(testController);
    }
}
