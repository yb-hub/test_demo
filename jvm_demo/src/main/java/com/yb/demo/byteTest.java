package com.yb.demo;

/**
 * @author yb
 * @description
 * @data 2021/5/7
 */
public class byteTest {
    public static void main(String[] args) {
        methodF();
    }

    private static void methodA() {
        int a = 1;
        methodB();
    }

    private static void methodB() {
        double b = 1.0;
    }

    private void methodC() {
        String str = "";
    }

    private void methodD() {
        int a = 1;
        {
            int b = 2;
        }
        int c = 3;
    }

    private void methodE() {
        byte a = 15;
        int b = 20;
        int c = a + b;
    }

    private static void methodF() {
        int i1 = 10;
        i1++;
        System.out.println(i1);
        int i2 = 10;
        ++i2;
        System.out.println(i2);
        int i3 = 10;
        int i4 = i3++;
        System.out.println(i4);
        int i5 = 10;
        int i6 = ++i5;
        System.out.println(i6);
        int i7 = 10;
        i7 = i7++;
        System.out.println(i7);
        int i8 = 10;
        i8 = ++i8;
        System.out.println(i8);
        int i9 = 10;
        int i10 = i9++ + ++i9;
        System.out.println(i10);
    }
}

