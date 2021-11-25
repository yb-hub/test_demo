package com.example.demo;

import com.example.demo.entity.FixParams;
import com.example.demo.entity.IMEIEntity;
import com.example.demo.entity.PersonEntity;
import com.yb.demo.result.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Auther: Yang
 * @Date: 2020/11/01 21:21
 * @Description:
 */
@RestController
@Slf4j
public class TestDemo {
    @PostMapping("/IMEITest")
    public CommonResult IMEITest(@RequestBody IMEIEntity imeiEntity){
      log.info("imeiEntity={}",imeiEntity);
      return null;
    }


    @PostMapping("/listTest")
    public CommonResult listTest(@RequestBody List<PersonEntity> personEntityList){
        log.info("personEntity={}",personEntityList.toString());
        return null;
    }

    @PostMapping("/fixParamsTest")
    public CommonResult fixParamsTest(@RequestBody FixParams fixParams){
        log.info("fixParams={}",fixParams.toString());
        return null;
    }

    public static void main(String[] args) {
        int[] arr = {1,2,5};
        int obj = 50;
        int min = getMin(obj);
        System.out.println(min);
    }

    private static int getMin(int obj) {
        Integer a=0,b=0,c=0;
        if(obj-1>=0){
            a = getMin(obj-1)+1;
        }
        if(obj-2>=0){
            b = getMin(obj-2)+1;
        }
        if(obj-5>=0){
            c = getMin(obj-5)+1;
        }
        return Math.min(Math.min(a,b),c);
    }
}
