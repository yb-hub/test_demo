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
//@ToString
public class Teacher extends Person implements Serializable {

    static final long serialVersionUID = 12346789l;

    private String name;
    public Teacher(){
        System.out.println("Teacher无参构造方法");
    }
    public Teacher(String name){
        this.name = name;
        System.out.println("Teacher有参构造方法");
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "name='" + name + '\'' +
                "} " + super.toString();
    }
}
