package com.yb.demo;

/**
 * @author yb
 * @description
 * @data 2021/11/10
 */
public class Thread100Demo {
    public static void main(String[] args) {
        String s1 = "s1";
        String s2 = "s2";
        String s3 = "s3";
        MyThread m1 = new MyThread("m1", s3,s1);
        MyThread m2 = new MyThread("m2", s1, s2);
        MyThread m3 = new MyThread("m3", s2, s3);
        new Thread(m1).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(m2).start();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(m3).start();
    }

}
