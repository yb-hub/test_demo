package com.yb.demo;

import java.util.Optional;

/**
 * @author yb
 * @description
 * @data 2020/8/5
 */
public class OptionalTest {
    public static void main(String[] args) {
        //使用Optional防止空指针
        Integer number1 = new Integer(1);
        Integer number2 = null;
        System.out.println(number1.toString());
        //System.out.println(number2.toString()); //会有空指针问题
        /**
         * Optional是一个容器，将对象放进容器中，设置为可以为null,调用orElse方法时，如果不为null，则返回原本的
         * 对象，否则返回新创建的默认对象
         */
        System.out.println(Optional.ofNullable(number2).orElse(new Integer(0)).toString());
    }
}
