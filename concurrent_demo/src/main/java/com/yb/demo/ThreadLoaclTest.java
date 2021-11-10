package com.yb.demo;


import java.util.ArrayList;
import java.util.HashMap;

/**
 * @author yb
 * @description
 * @data 2021/7/20
 */
public class ThreadLoaclTest {
    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                threadLocal.set("threadName:" + Thread.currentThread().getName());
                String s = threadLocal.get();
                System.out.println(s);
            }).start();
        }
        Thread thread = Thread.currentThread();
        //thread.threadLocals;
        VolatileTest volatileTest = new VolatileTest();
        int test = volatileTest.test;

    }
}
