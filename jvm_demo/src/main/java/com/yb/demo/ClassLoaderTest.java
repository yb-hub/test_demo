package com.yb.demo;

import com.sun.java.accessibility.AccessBridge;

/**
 * @author yb
 * @description
 * @data 2021/5/6
 */
public class ClassLoaderTest {
    public static void main(String[] args) throws ClassNotFoundException {
        //bootStrapClassLoader
        ClassLoader classLoader = Class.forName("java.lang.String").getClassLoader();
        System.out.println(classLoader);
        //extClassLoader  JAVA_HOME/jre/lib/ext/目录下
        ClassLoader classLoader1 = AccessBridge.class.getClassLoader();
        System.out.println(classLoader1);
        //appClassLoader
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
    }
}
