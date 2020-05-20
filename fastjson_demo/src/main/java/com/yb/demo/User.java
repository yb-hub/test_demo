package com.yb.demo;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author yb
 * @description 用户类
 * @data 2020/5/19
 */
@Data
@AllArgsConstructor
public class User {
    private String name;
    private int age;
}
