package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.xml.ws.RequestWrapper;

/**
 * @author yb
 * @description
 * @data 2020/6/3
 */
@RestController
public class TestController {
    @PostMapping("/test")
    public void test(@RequestParam(value = "name") String name,
                     @RequestBody User user){
        System.out.println("Name:"+name);
        System.out.println("User:"+user.name);
    }
}
