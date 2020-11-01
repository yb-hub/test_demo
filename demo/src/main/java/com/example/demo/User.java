package com.example.demo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

/**
 * @author yb
 * @description
 * @data 2020/5/21
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    public void init(){
        System.out.println("=============");
    }

    public void destroy(){
        System.out.println("destroy");
    }

    String name;

    //String age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                '}';
    }
}
