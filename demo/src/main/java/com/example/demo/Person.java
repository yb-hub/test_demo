package com.example.demo;

/**
 * @author yb
 * @description
 * @data 2020/12/22
 */
public class Person {
    public void say(){
        System.out.println("Person say");
        this.sayHello();
        sayHello();
    }

    public void sayHello(){
        System.out.println("Person say Hello");
    }
}
