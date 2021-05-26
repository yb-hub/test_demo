package com.yb.demo;

import javax.sound.midi.Soundbank;
import javax.xml.bind.SchemaOutputResolver;

/**
 * @author yb
 * @description clint测试类
 * @data 2021/4/30
 */
public class ClinitTest {

    static {
        System.out.println(ClinitTest.name);
        System.out.println(ClinitTest.name2);
        System.out.println(ClinitTest.name3);
        System.out.println(ClinitTest.age);
    }
    public static String name = "test1";
    public static String name2;
    public static final String name3 = "test2";
    public static int age;
    static {
        System.out.println(name);
        System.out.println(name2);
        System.out.println(name3);
        System.out.println(age);
    }
    public static void main(String[] args) {
        new Thread(()->{
            new ClinitTest2();
        }).start();
        new Thread(()->{
            new ClinitTest2();
        }).start();
    }
}
class ClinitTest2{
    static {
        System.out.println(Thread.currentThread().getName());
        for (int i = 0; i < 100; i++) {
            i = 0;
        }
    }
}
