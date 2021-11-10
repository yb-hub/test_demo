package com.example.demo;

import com.example.demo.entity.IMEIEntity;
import com.yb.demo.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Auther: Yang
 * @Date: 2020/11/01 21:21
 * @Description:
 */
@RestController("/test")
@Slf4j
public class TestDemo {
    @PostMapping("/IMEITest")
    public CommonResult IMEITest(@RequestBody IMEIEntity imeiEntity){
      log.info("imeiEntity={}",imeiEntity);
      return null;
    }
}
