package com.yb.demo;

/**
 * @author yb
 * @description 最长有效括号
 * @data 2021/5/20
 */
public class LongestValidParentheses {
    public static void main(String[] args) {
        String str = "";
        longestValidParentheses(str);
    }
    public static int longestValidParentheses(String s) {
        int maxLength = 0;
        for (int i = 0; i < s.length(); i++) {
            if(s.charAt(i) == '('){
                maxLength++;
                //余数为1则为奇数 0为偶数
                boolean isOdd = i%2 == 1 ? true : false;
                while(true){
                    i++;
                    //if(i%2)
                }
            }
        }
        return maxLength;
    }
}
