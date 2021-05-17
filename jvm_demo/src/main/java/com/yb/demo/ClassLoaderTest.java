package com.yb.demo;

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
        //appClassLoader
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println(contextClassLoader);
        //extClassLoader
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        ClassLoader parent = systemClassLoader.getParent();
        System.out.println(systemClassLoader);
        System.out.println(parent);
    }
}
