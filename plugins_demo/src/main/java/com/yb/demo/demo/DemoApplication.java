package com.yb.demo.demo;

import com.yb.demo.demo.controller.pluginsUploadController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class DemoApplication {

    @Autowired
    private pluginsUploadController pluginsUploadController;

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(DemoApplication.class, args);
        Object pluginsUploadController = context.getBean("pluginsUploadController");
        System.out.println(context);
    }

}
