package com.yb.demo;

import lombok.extern.slf4j.Slf4j;
import sun.rmi.runtime.Log;

import java.util.Scanner;

/**
 * @author yb
 * @description
 * @data 2020/7/13
 */
@Slf4j
public class VolatileTest {

    int test = 1;

    private static  int count = 0;

    public static void main(String[] args) throws InterruptedException {
        new Thread(()-> {for (int i = 0; i < 100000; i++) {
            count++;
        }}).start();
        new Thread(()-> {for (int i = 0; i < 100000; i++) {
            count++;
        }}).start();
        Thread.sleep(10000);
        System.out.println(count);
    }
}
