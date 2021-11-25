package com.example.demo.controller;

import com.alibaba.fastjson.JSON;
import com.example.demo.TestApplication;
import com.example.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author yb
 * @description
 * @data 2021/9/1
 */
@RestController
@Slf4j
public class TestController {

    @Autowired
    private TestController testController;

    @PostMapping("/test")
    public Object test(@RequestBody Map map){
      log.info("map={}",map);
        TestController testController1 = (TestController) TestApplication.ctx.getBean("testController");
        String jsonString = JSON.toJSONString(map);
        Person person = JSON.parseObject(jsonString,Person.class);
        log.info("person={}",person);
        return person;
    }
}
