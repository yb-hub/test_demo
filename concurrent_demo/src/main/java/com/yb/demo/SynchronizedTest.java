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

    public synchronized void read(){
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(number);
    }
    public static void main(String[] args) {

        new Thread(()->{
            SynchronizedTest synchronizedTest = new SynchronizedTest();
            synchronizedTest.write();
        }).start();
        new Thread(()->{
            SynchronizedTest synchronizedTest = new SynchronizedTest();
            synchronizedTest.read();
        }).start();
    }
}
