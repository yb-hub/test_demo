package com.yb.demo;

import lombok.extern.slf4j.Slf4j;

/**
 * @author yb
 * @description 线程睡眠测试
 * @data 2020/6/30
 */

/**
 * sleep和yield区别：sleep会有指定时间，sleep后，线程状态从running->TIMED_WAITING
 * yield:是将当前线程从running->runnable,然后cpu就可以调度其余的线程（也可能调度当前线程）,相当于让出位置，给其他线程
 */
@Slf4j
public class ThreadSleepTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                log.info("线程状态4:{}",Thread.currentThread().getState());
                //当线程睡眠时被中断，会抛出异常
                e.printStackTrace();
            }
        }, "thread1");
        log.info("线程状态1:{}",thread1.getState());
        thread1.start();
        Thread.sleep(1000);
        log.info("线程状态2:{}",thread1.getState());
        thread1.interrupt();
        Thread.sleep(3000);
        log.info("线程状态3:{}",thread1.getState());
    }
}
