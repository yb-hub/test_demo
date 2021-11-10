package com.yb.demo;

import java.lang.String;

/**
 * @author yb
 * @description
 * @data 2021/5/6
 */
public class StringTest {
    private final String aa = "aa";
    public static void main(String[] args) {
//        String string = new String("string");
//        System.out.println(string);
//        String1 string1 = new String1();
//        System.out.println(string1);

        String c = "c";
        String s1 = "a"+"b"+c;
        String s2 = "abc";
        System.out.println(s1==s2);
        System.out.println(s1.equals(s2));

        String a = new String("a").intern();
        String b = new String("a").intern();
        System.out.println(a==b);


        String mn = new String("m") + new String("n");
        mn.intern();
        String mn2 = "mn";
        System.out.println(mn == mn2);

        String testStr = new String("testStr");
    }
}
//new String()
