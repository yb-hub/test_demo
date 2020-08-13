package com.yb.demo.serialized;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yb
 * @description 无参构造
 * @data 2020/8/13
 */
@Data
public class PersonNoConstructor implements Serializable {
    private int id;

    /**
     * 有参构造函数
     * @param id
     */
    public PersonNoConstructor(int id){
        System.out.println("PersonNoConstructor有参构造方法");
        this.id = id;
    }
}
