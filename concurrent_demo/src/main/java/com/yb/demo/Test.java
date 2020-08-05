package com.yb.demo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author yb
 * @description
 * @data 2020/6/28
 */
public class Test {
    static Long count = new Long(0);
    public static void main(String[] args) {

        ArrayList<Object> list = new ArrayList<>();
        HashMap<Object, Object> map = new HashMap<>();
        Hashtable<Object, Object> table = new Hashtable<>();
        Object key = map.get("key");
        Object put = map.put("key", "test");

        int n1 = 0x2211 & 0xffff;

        String s = Integer.toHexString(8721);

        int i = Integer.parseInt("9999",16);

        String testStr = "12345678910";
        String s1 = testStr.substring(0, 7);
        String s2 = testStr.substring(7);

        long start = System.currentTimeMillis();
        System.out.println("起始时间："+start);
        System.out.println(count);
        test1();
        long end = System.currentTimeMillis();
        System.out.println("结束时间："+end);
        System.out.println(count);
        System.out.println("耗时："+(end-start));
    }

    private static void test1() {
        ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(32, 32, Long.MAX_VALUE, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

        for (int i = 0; i < 32; i++) {
            poolExecutor.execute(()->{
                    while(true){
                        for (long j = 0; j < 1000000; j++) {

                        }
                        synchronized (Test.class){
                            if(count < 5000){
                                count++ ;
                            }

                        }
                    }
            });
        }
        while(true){
            System.out.println(count);
            if(count >= 5000){
                break;
            }
        }
    }
}
