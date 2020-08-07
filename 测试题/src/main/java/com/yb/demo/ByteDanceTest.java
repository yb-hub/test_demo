package com.yb.demo;

/**
 * @author yb
 * @description
 * @data 2020/8/6
 */
public class ByteDanceTest {
    public static void main(String[] args) {
        String str = "aabbddddddbbb";
        ByteDanceTest.violence(str);

    }

    public static void violence(String str){

        StringBuilder sb = new StringBuilder(str);
        //去除三个连续
        for (int i = 0; i < sb.length()-3; i++) {
            if(sb.charAt(i) == sb.charAt(i+1)){
                //aabb型
                    if((sb.charAt(i+2) == sb.charAt(i+3) && sb.charAt(i+2)!=sb.charAt(i))){
                        sb.deleteCharAt(i+3);
                        i--;
                    }
                }
                //aaa型
                if(sb.charAt(i+1) == sb.charAt(i+2) && sb.charAt(i+2) == sb.charAt(i+3)) {
                    sb.deleteCharAt(i+3);
                    i--;
                }
        }
        System.out.println(sb);
    }

    //有限状态自动机

    /**
     * 一共有三种状态：1.a/b/ab/ba  2.aaa/bbb  3.aabb
     * @param str
     */
    public static void deal(String str){
        char[] charArray = str.toCharArray();
        StringBuilder sb = new StringBuilder(str);
        int state = 1;
        System.out.println(sb.length());
        for (int i = 1; i < sb.length(); i++) {
            switch (state){
                case 1:
                    if(sb.charAt(i)== sb.charAt(i-1)) {
                        state = 2;
                    }
                    break;
                case 2:
                    if(sb.charAt(i)== sb.charAt(i-1)){
                        sb.deleteCharAt(i);
                        i--;
                    }else{
                        state = 3;
                    }
                    break;
                case 3:
                    if(sb.charAt(i)== sb.charAt(i-1)){
                        sb.deleteCharAt(i);
                        i--;
                    }else{
                        state = 1;
                    }
                    break;
            }
        }
        System.out.println(sb);
    }


    //正则匹配
    public static void regex(String str){
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
