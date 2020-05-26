package com.example.demo;

/**
 * @author yb
 * @description
 * @data 2020/5/21
 */

public class User {
    public void init(){
        System.out.println("=============");
    }

    public void destroy(){
        System.out.println("destroy");
    }
    String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
