package com.yb.demo;



import java.util.ArrayList;

/**
 * @author yb
 * @description 字符串常量池测试类
 * @data 2021/5/19
 */
public class StringTableTest {

    public static void main(String[] args) throws InterruptedException {
//        String str = new String("a")+new String("b");
//        str.intern();
//        String s1 = "ab";
//        System.out.println(str == s1);
        String str = "1";
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0;; i++) {
            Thread.sleep(1);
            String timeStr = new String("test");
            //list.add(timeStr);
            //System.out.println(i);
        }
    }
}
