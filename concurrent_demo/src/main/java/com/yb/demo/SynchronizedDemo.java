package com.yb.demo;

/**
 * @Auther: Yang
 * @Date: 2020/07/19 22:43
 * @Description:
 */
public class SynchronizedDemo {
    public static void main(String[] args) {

        Object lock = new Object();

        Thread t1 = new Thread(()->{
            synchronized (lock){
                System.out.println("成功获取锁");
            }
        });

        //在主线程中先获取锁
        synchronized (lock){
            t1.start();
            //中断t1
            System.out.println("t1的state:"+t1.isInterrupted());
            t1.interrupt();
            System.out.println("t1的state:"+t1.isInterrupted());
        }
    }
}
