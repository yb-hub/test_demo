package com.yb.demo;

/**
 * @author yb
 * @description
 * @data 2020/7/13
 */
public class VolatileTest {
    static boolean running = true;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            while(running){

            }
        }).start();
        Thread.sleep(10);
        running = false;
    }
}
