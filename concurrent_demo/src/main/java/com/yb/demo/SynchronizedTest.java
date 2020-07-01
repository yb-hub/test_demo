package com.yb.demo;

/**
 * @author yb
 * @description synchronized关键字测试demo
 * @data 2020/6/24
 */
public class SynchronizedTest {
    int number = 0;

    public synchronized void write(){
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        number ++;
    }

    public  void read(){
        System.out.println(number);
    }
    public static void main(String[] args) {
        SynchronizedTest synchronizedTest = new SynchronizedTest();
        new Thread(()->{
            synchronizedTest.write();
        }).start();
        new Thread(()->{
            synchronizedTest.read();
        }).start();
    }
}
