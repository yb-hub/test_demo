package com.yb.demo;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author yb
 * @description
 * @data 2021/12/2
 */
@Slf4j
public class CyclicBarrierDemo {
    public static void main(String[] args) {
//        CyclicBarrier cyclicBarrier = new CyclicBarrier(10, () -> {
//            log.info("集齐10个，任务完成");
//        });
        CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
        for (int i = 0; i < 10; i++) {
            new Thread(()->{
                log.info(Thread.currentThread().getName()+"帮忙砍了一刀");
                try {
                    cyclicBarrier.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            },"线程"+String.valueOf(i)).start();
        }
    }
}
