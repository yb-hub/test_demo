package com.yb.demo.controller;

import com.yb.demo.annotation.LogAnnotation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yb
 * @description 测试类
 * @data 2021/6/3
 */
@RestController
@Slf4j
public class TestController {
    @GetMapping("/test1")
    @LogAnnotation(operation = "test1")
    public String test1(@RequestParam("param") String param){
      log.info("test1:{}",param);
      return "test1 is ok";
    }
}
