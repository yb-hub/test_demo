package com.yb.demo;


import java.net.SocketTimeoutException;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author yb
 * @description
 * @data 2020/7/13
 */
public class ReentrantLockDemo {
    static ReentrantLock lock = new ReentrantLock();
    public static void main(String[] args) throws InterruptedException {

        Thread t1 = new Thread(() -> {
            lock.lock();
//            try {
//                lock.lockInterruptibly();
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            try {
                System.out.println(Thread.currentThread().getName() + "成功获取锁，状态为：" + Thread.currentThread().getState());
            } finally {
                lock.unlock();
            }
        });

        System.out.println(Thread.currentThread().getName()+"线程状态："+Thread.currentThread().getState());
        lock.lock();
//        try {
//            //lock.lockInterruptibly();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        try {
            System.out.println("加锁成功");
            System.out.println(Thread.currentThread().getName()+"线程状态："+Thread.currentThread().getState());
            t1.start();
            Thread.sleep(10);
            System.out.println("t1线程状态:"+t1.getState());
            System.out.println("t1线程中断状态1："+t1.isInterrupted());
            t1.interrupt();
            System.out.println("t1线程中断状态2："+t1.isInterrupted());
            Thread.sleep(10000);
        }finally {
            lock.unlock();
        }

    }
}
