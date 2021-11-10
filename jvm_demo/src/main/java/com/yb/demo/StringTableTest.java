package com.yb.demo;



/**
 * @author yb
 * @description 字符串常量池测试类
 * @data 2021/5/19
 */
public class StringTableTest {

    public static void main(String[] args) {
        String str = new String("a")+new String("b");
        str.intern();
        String s1 = "ab";
        System.out.println(str == s1);
    }
}
