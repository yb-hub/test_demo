package com.yb.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;

/**
 * @Auther: Yang
 * @Date: 2021/08/29 13:22
 * @Description:
 */
@SpringBootApplication(scanBasePackages = {"com.ybb"})
public class WebsocketApplication {
    public static void main(String[] args) {
        SpringApplication.run(WebsocketApplication.class);
    }
}
