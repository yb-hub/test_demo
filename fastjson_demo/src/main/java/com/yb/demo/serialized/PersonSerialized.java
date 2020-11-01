package com.yb.demo.serialized;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yb
 * @description
 * @data 2020/8/13
 */
@Data
public class PersonSerialized implements Serializable {
    private int id;
    public PersonSerialized(){
        System.out.println("PersonSerialized无参构造方法");
    }
}
