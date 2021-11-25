package com.yb.demo;

import javax.jws.soap.SOAPBinding;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
 * @author yb
 * @description list, set, map线程安全测试
 * @data 2021/11/18
 */
public class CollectTest {


    public static void main(String[] args) throws InterruptedException {
        iteratorTest();
        //arrayListTest();
        //vectorTest();
        //arrayListTest2();
    }

    private static void arrayListTest2() {
        ArrayList<Integer> ls = new ArrayList<>();
        List<Integer> list = Collections.synchronizedList(ls);
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
        listTest(list);

    }

    private static void vectorTest() {
        Vector<Integer> list = new Vector<>();
//        list.add(1);
//        list.add(2);
//        list.add(3);
//        list.add(4);
//        list.add(5);
        Iterator<Integer> iterator = list.iterator();
//        while (iterator.hasNext()){
//            Integer num = iterator.next();
//            if(num == 2){
//                list.remove(num);
//            }
//            System.out.println(num);
//        }

        listTest(list);

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
        listTest(list);
    }

    private static void arrayListTest() throws InterruptedException {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<Integer>();
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
        listTest(list);
    }

    private static void listTest(List list){
        for (int i = 0; i < 30; i++) {
            new Thread(()->{
                list.add(UUID.randomUUID());
                System.out.println(list);
            }).start();
        }
    }
}
