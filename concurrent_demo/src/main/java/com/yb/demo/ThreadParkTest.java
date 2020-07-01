package com.yb.demo;

import java.util.concurrent.locks.LockSupport;

/**
 * @author yb
 * @description 线程park测试
 * @data 2020/6/30
 */
public class ThreadParkTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(()->{
            System.out.println("线程状态1："+Thread.currentThread().getState());
            LockSupport.park();
            System.out.println("线程状态2："+Thread.currentThread().getState());
        },"thread1");
        thread1.start();

        Thread.sleep(1000);
        System.out.println("线程状态3："+thread1.getState());
        LockSupport.unpark(thread1);
    }
}
