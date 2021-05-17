package com.yb.demo;

/**
 * @author yb
 * @description 非主动使用类字段
 * @data 2021/4/2
 */
public class NotInitialization {

    public static void main(String[] args) {
        test3();
    }

    //对于静态字段，只有直接定义这个字段的类才会被初始化。
    //数据结果：
    //this is SuperClass
    //1
    public static void test1(){
        System.out.println(SubClass.value);
    }

    public static void  test2(){
        //
        //输出结果：
        //
        SuperClass[] arr = new SuperClass[10];
    }

    public static void test3(){
        //编译阶段经过常量优化，将常量保存在NotInitialization类常量池中，所以NotInitialization的文件中没有ConstClass类的符号引用入口
        //输出结果：
        //constClass
        System.out.println(ConstClass.str);
    }
}
