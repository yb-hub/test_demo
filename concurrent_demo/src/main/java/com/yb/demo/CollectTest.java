package com.yb.demo;

import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author yb
 * @description list, set, map线程安全测试
 * @data 2021/11/18
 */
public class CollectTest {


    public static void main(String[] args) throws InterruptedException {
        iteratorTest();
        //arrayListTest();
    }

    /**
     * 迭代器测试
     */
    private static void iteratorTest() {
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);
        list.add(5);
        Iterator<Integer> iterator = list.iterator();
        while (iterator.hasNext()){
            Integer num = iterator.next();
            if(num == 2){
                list.remove(num);
            }
            System.out.println(num);
        }
    }

    private static void arrayListTest() throws InterruptedException {
        AtomicInteger number =  new AtomicInteger();
        ArrayList<Integer> list = new ArrayList<>();
        Iterator<Integer> iterator = list.iterator();
        //CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        for (int i = 0; i < 300; i++) {
            new Thread(()->{
                list.add(number.getAndIncrement());
                //System.out.println(list.get(0));
            }).start();
        }
        Thread.sleep(1000);
        System.out.println(list);
    }
}
