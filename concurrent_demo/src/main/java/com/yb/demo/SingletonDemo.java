package com.yb.demo;

import java.util.concurrent.CountDownLatch;

/**
 * @author yb
 * @description DCL（double check lock 双端检索）
 * @data 2021/11/16
 */
public class SingletonDemo {

    private static SingletonDemo instance = null;

    public SingletonDemo() {
        System.out.println("init singleton");
    }

//    public static synchronized SingletonDemo getInstance(){
//        if(instance == null){
//            instance =  new SingletonDemo();
//        }
//        return instance;
//    }

    /**
     * DCL
     * @return
     */
    public static SingletonDemo getInstance(){
        if(instance == null){
            synchronized (SingletonDemo.class){
                if(instance == null){
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

    public static void main(String[] args) {
        for (int i = 0; i < 10000000; i++) {
            new Thread(()->{
                SingletonDemo.getInstance();
            }).start();
        }
    }
}
