package com.example.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Auther: Yang
 * @Date: 2020/11/01 21:42
 * @Description:
 */
@SpringBootApplication
@Slf4j
public class TestApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(TestApplication.class);
        log.info(String.valueOf(context.getBeanFactory().getBean("iMEIEntity")));
    }
}
