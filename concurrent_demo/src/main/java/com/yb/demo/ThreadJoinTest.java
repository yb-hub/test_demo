package com.yb.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author yb
 * @description 线程join方法测试
 * @data 2020/6/30
 */

/**
 * 使用join来等待线程结束，基本原理，内部不断循环判断线程状态是否alive。如果为false，则表示线程结束。
 */
public class ThreadJoinTest {
    static int count = 0;
    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = 10;
        });
        Thread thread2 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            count = 10;
        });
        thread1.start();
        thread2.start();
        //使用join等待线程运行结束,可以设定时间
        //thread1.join();
        thread1.join(500);
        thread2.join();
        System.out.println(count);
        long end = System.currentTimeMillis();
        System.out.println("执行时间："+(end-start));
    }
}
