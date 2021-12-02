package com.yb.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yb
 * @description
 * @data 2021/12/2
 */
@Slf4j
public class CountDownLatchDemo {
    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0; i < 100; i++) {
            new Thread(()->{
                log.info(Thread.currentThread().getName()+"帮忙砍了一刀");
                countDownLatch.countDown();
            },"线程"+String.valueOf(i)).start();
        }
        countDownLatch.await();
        System.out.println("砍完10刀，完成任务");
    }
}
