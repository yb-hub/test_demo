package com.yb.demo;

import javax.lang.model.element.VariableElement;

/**
 * @author yb
 * @description abc三个线程交替打印
 * @data 2021/10/18
 */
public class ThreadABCDemo {
    private String lockA="a";
    private String lockB="b";
    private String lockC="c";

    public static void main(String[] args) throws InterruptedException {
        ThreadABCDemo threadABCDemo = new ThreadABCDemo();
        MyThread threadA = new MyThread("a",threadABCDemo.lockC,threadABCDemo.lockA);
        MyThread threadB = new MyThread("b",threadABCDemo.lockA,threadABCDemo.lockB);
        MyThread threadC = new MyThread("c",threadABCDemo.lockB,threadABCDemo.lockC);

        new Thread(threadA).start();
        Thread.sleep(10);
        new Thread(threadB).start();
        Thread.sleep(10);
        new Thread(threadC).start();
    }
}
