package com.yb.demo.serialized;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @author yb
 * @description
 * @data 2020/8/13
 */
@Data
public class Person implements Serializable {
    private int id;

    public Person(){
        System.out.println("person无参构造方法");
    }

    @Override
    public String toString() {
        return "Person{" +
                "id=" + id +
                '}';
    }

        public Person(int id){
        System.out.println("person有参构造方法");
    }
}
