package com.yb.demo.aop;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author yb
 * @description 日志切面
 * @data 2021/6/3
 */
@Component
@Aspect
@Slf4j
public class LogAop {

    @Pointcut("@annotation(com.yb.demo.annotation.LogAnnotation)")
    private void pointCut(){}

    @Before("com.yb.demo.aop.LogAop.pointCut()")
    private void before(){
        log.info("before...");
    }

    @After("com.yb.demo.aop.LogAop.pointCut()")
    private void after(){
        log.info("after...");
    }

    @AfterReturning(value = "com.yb.demo.aop.LogAop.pointCut()",returning = "result")
    private void afterReturn(String result){
        log.info("afterReturn...,result={}",result);
    }

    @Around("com.yb.demo.aop.LogAop.pointCut()")
    private void around(ProceedingJoinPoint pjp){
        log.info("around...,pjp={}",pjp);
        log.info("params={}",pjp.getArgs());
        log.info("methodName={}",pjp.getSignature().getName());
    }

    public static void main(String[] args) {
        LocalDate now = LocalDate.now();
        LocalDate date = now.plusDays(10);
        System.out.println(now.compareTo(date));
    }
}
