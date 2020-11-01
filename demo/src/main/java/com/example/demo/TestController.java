package com.example.demo;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

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


    public static void main(String[] args) throws IOException {
        String jsonStr = "{\"age\":18,\"name\":\"zhangsan\"}";
        //使用fastjson
        User user = JSON.parseObject(jsonStr, User.class);
        System.out.println(user);
        //使用jackson
        ObjectMapper om = new ObjectMapper();
        User user1 = om.readValue(jsonStr, User.class);
        System.out.println(user1);
    }
}
