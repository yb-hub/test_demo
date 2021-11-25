package com.yb.demo.controller;

import com.yb.demo.client.MyClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yb
 * @description
 * @data 2021/10/9
 */
@Slf4j
@RestController
public class ForestController {
    @Autowired
    private MyClient myClient;

    @GetMapping("/getTest")
    public ResponseEntity getTest(){
        log.info("http调用远程接口");
        String result = myClient.getTest();
        log.info("远程调用结果，result={}",result);
        return ResponseEntity.ok("success");
    }
}
