package com.example.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yb
 * @description
 * @data 2020/5/21
 */
@Configuration
public class Config {
    @Bean(initMethod = "init",destroyMethod = "destroy")
    public User user(){
        return new User();
    }
}
