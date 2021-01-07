package com.example.demo;

/**
 * @author yb
 * @description
 * @data 2020/12/22
 */
public class Student extends Person {
//    @Override
//    public void say(){
//        System.out.println("Student say");
//    }

    @Override
    public void sayHello(){
        System.out.println("Student say hello");
    }

    public static void main(String[] args) {
        Student student = new Student();
        student.say();
    }
}
