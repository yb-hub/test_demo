package com.yb.demo;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import javax.lang.model.element.VariableElement;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yb
 * @description
 * @data 2020/7/13
 */

/**
 * 1.轻量级的同步机制
 * 1.1 保证可见性
 * 1.2 不保证原子性
 * 1.3 禁止指令重排
 */
@Slf4j
public class VolatileTest {

    //int testNum1 = 1;
    AtomicInteger testNum1 = new AtomicInteger();
    volatile int testNum2 = 2;


    public static void main(String[] args) throws InterruptedException {
        //可见性测试();
        原子性测试();
    }

    @SneakyThrows
    private static void 原子性测试() {
        VolatileTest volatileTest = new VolatileTest();
        new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                volatileTest.testNum1.incrementAndGet();
                //volatileTest.testNum1 ++;
                volatileTest.testNum2 ++;
            }
        }).start();
        new Thread(()->{
            for (int i = 0; i < 100000; i++) {
                volatileTest.testNum1.incrementAndGet();
                //volatileTest.testNum1 ++;
                volatileTest.testNum2 ++;
            }
        }).start();
        Thread.sleep(1000);
        System.out.println(volatileTest.testNum1);
        System.out.println(volatileTest.testNum2);
    }

//    public static void 可见性测试(){
//        VolatileTest volatileTest = new VolatileTest();
//        new Thread(()->{
//            int number1 = volatileTest.testNum1;
//            int number2 =  volatileTest.testNum2;
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            volatileTest.testNum1 = 100;
//            volatileTest.testNum2 = 200;
//            System.out.println("number1:"+number1);
//            System.out.println("number2:"+number2);
//        }).start();
//
//        new Thread(()->{
//            while(volatileTest.testNum1 == 1){
//                System.out.println(volatileTest.testNum1);
//            }
//            System.out.println("testNum1 change");
//        }).start();
//
//        new Thread(()->{
//            while(volatileTest.testNum2 == 2){
//
//            }
//            System.out.println("testNum2 change");
//        }).start();
//    }
}
