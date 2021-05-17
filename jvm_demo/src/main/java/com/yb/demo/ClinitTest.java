package com.yb.demo;

import javax.sound.midi.Soundbank;
import javax.xml.bind.SchemaOutputResolver;

/**
 * @author yb
 * @description clint测试类
 * @data 2021/4/30
 */
public class ClinitTest {

    public static String name = "test1";
    public int age = 18;

    static {
        System.out.println("ClinitTest....");
        for (int i = 0; i < 100; i++) {
            i = 0;
        }
    }


    public static void main(String[] args) {
        new Thread(()->{
            System.out.println(ClinitTest.name);
        }).start();
        new Thread(()->{
            System.out.println(ClinitTest.name);
        }).start();
    }
}
