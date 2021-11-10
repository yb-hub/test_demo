package com.yb.demo;

/**
 * @author yb
 * @description 常量类
 * @data 2021/4/2
 */
public class ConstClass {

    public static String name = "test";

    static {
        System.out.println("this is ConstClass");
    }

    public static final String str = "constClass";

    public static void main(String[] args) {
        //System.out.println(ClinitTest.name);
        ConstClass constClass = new ConstClass();
        System.out.println("1");
    }
}
