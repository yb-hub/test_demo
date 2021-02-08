package com.yb.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author yb
 * @description
 * @data 2021/1/19
 */
public class JVMTest {
    //System.out.println(111);循环一百万次大概2s 无打印语句几乎0s

    private static Logger logger = LoggerFactory.getLogger(JVMTest.class);

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        while(true){
            String str = new String(String.valueOf(i++));
            use(str);
        }
    }

    private static void use(String str) {

    }
}
