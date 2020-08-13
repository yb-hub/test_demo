package com.yb.demo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author yb
 * @description 用户类
 * @data 2020/5/19
 */
@Data
//@AllArgsConstructor
//@NoArgsConstructor
public class User {
    private String name;
    private int age;

    public User(String name){
        this.name = name;
    }
}
