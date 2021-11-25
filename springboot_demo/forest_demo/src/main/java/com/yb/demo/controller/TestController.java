package com.yb.demo.controller;

import com.yb.demo.entity.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * @author yb
 * @description
 * @data 2021/10/9
 */
@Slf4j
@RestController
@RequestMapping("/forest/api")
public class TestController {
    @GetMapping("/get")
    public ResponseEntity get(String param1,String param2){
      log.info("param1={},param2={}",param1,param2);
      return ResponseEntity.ok("success");
    }

    @PostMapping("/post")
    public ResponseEntity post(@RequestBody @Validated Person person){
        return ResponseEntity.ok("success");
    }

    public static void main(String[] args) {
        if("a" == null){
            System.out.println("true");
        }
    }
}
