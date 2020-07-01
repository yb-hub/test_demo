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

    @GetMapping("/test2/{id}")
    public void test2(@PathVariable("id") Integer id, String name,User user){
        System.out.println("Name:"+name);
        System.out.println("User:"+user.name);
    }

    /**
     * 测试get请求中 使用@requestbody
     * @param name
     * @param user
     */
    @GetMapping("/test3")
    public void test3(String name,@RequestBody User user){
        System.out.println("Name:"+name);
        System.out.println("User:"+user.name);
    }

    @GetMapping("/test4/{id2}")
    public void test4(@PathVariable Integer id){
        System.out.println(id);
    }
}
