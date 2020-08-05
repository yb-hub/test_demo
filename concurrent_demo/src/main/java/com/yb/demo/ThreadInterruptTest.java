package com.yb.demo;

import java.util.concurrent.TimeUnit;

/**
 * @author yb
 * @description 线程interrupt测试
 * @data 2020/6/30
 */

/**
 * 使用interrupt中断线程时，如果线程是正常运行的话，则isInterrupted标志为true.
 * 如果线程是处于sleep,wait,park等阻塞状态的话，则会清除标志状态，isInterrupt为false.
 */

/**
 * 可以使用interrupt方法，优雅的终止线程。
 * 使用isInterrupted判断是否打断，不会清除打断标记
 * 也可以使用thread的static方法，interrupted，会清除打断标记
 */
public class ThreadInterruptTest {
    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> {
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                System.out.println("isInterrupt:" + Thread.currentThread().isInterrupted());
                e.printStackTrace();
            }
        });

        Thread thread2 = new Thread(() -> {
            while (true) {
                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("isInterrupt2:" + Thread.currentThread().isInterrupted());
                    break;
                }
                //如果有具体业务，比如不断消费kafka内数据等，这里1==1只是举例
                if(1==2){
                    System.out.println("随便做点什么。。。");
                }else {
                    //如果条件不成立，为了不一直占用cpu,让线程阻塞
                    //如果此时线程被中断，则会抛出异常，此时isInterrupt为false,可在catch中设置为true;等下一次循环则可以终止线程。
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        System.out.println("阻塞时被终止");
                        Thread.currentThread().interrupt();
                        e.printStackTrace();
                    }
                }
            }
        });
        thread1.start();
        thread2.start();

        TimeUnit.SECONDS.sleep(1);
        thread1.interrupt();
        thread2.interrupt();
    }
}
