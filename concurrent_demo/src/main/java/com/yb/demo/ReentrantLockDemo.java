package com.yb.demo;

import java.util.concurrent.locks.ReentrantLock;

/**
 * @Auther: Yang
 * @Date: 2020/07/19 22:35
 * @Description:
 */
public class ReentrantLockDemo {
    public static void main(String[] args) throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Thread t1 = new Thread(() -> {
            try {
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                System.out.println("t1线程被打断了");
                e.printStackTrace();
            }
        }, "t1");

        //先让主线程获取锁
        lock.lock();
        //然后启动t1线程，让它去竞争锁
        t1.start();
        Thread.sleep(1000);
        //在竞争锁的过程中中断t1线程
        t1.interrupt();
    }
}
