package com.yb.demo;

import jdk.nashorn.internal.runtime.regexp.joni.Regex;

import java.util.regex.Pattern;

/**
 * @author yb
 * @description 正则demo
 * @data 2020/8/7
 */
public class RegexDemo {
    public static void main(String[] args) {
        String str = "aaaabbbcc";

        //匹配三个连续相同的字符
        /**
         * \1比配第一个括号中的结果，\2匹配第二个括号中的结果
         * {2}表示之前的一个字符重复两次
         * {2,}表示至少重复2次
         */
        String result = str.replaceAll("([a-z])\\1{2,}", "$1$1");
        //System.out.println(result);
        //匹配aabb型字符
        String result2 = result.replaceAll("([a-z])\\1([a-z])\\2", "$1$1$2");
        System.out.println(result2);
    }
}
