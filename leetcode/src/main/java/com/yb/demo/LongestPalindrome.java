package com.yb.demo;

/**
 * @author yb
 * @description 最长回文串
 * 给你一个字符串 s，找到 s 中最长的回文子串
 * 示例 1：
 * <p>
 * 输入：s = "babad"
 * 输出："bab"
 * 解释："aba" 同样是符合题意的答案。
 * 示例 2：
 * <p>
 * 输入：s = "cbbd"
 * 输出："bb"
 * 示例 3：
 * <p>
 * 输入：s = "a"
 * 输出："a"
 * 示例 4：
 * <p>
 * 输入：s = "ac"
 * 输出："a"
 * @data 2021/5/14
 */
public class LongestPalindrome {
    public static void main(String[] args) {
        String str = "";
        System.out.println(longestPalindrome(str));
    }

    /**
     * 穷举 遍历str,以每个字符为中心，向两边遍历
     *
     * @param str
     * @return
     */
    private static String longestPalindrome(String str) {
        String stringBuilder = "";
        if (str.length() == 1) {
            return str;
        }
        //遍历str
        for (int i = 0; i < str.length() - 1; i++) {
            String result = search(i, str);
            if (result.length() > stringBuilder.toString().length()) {
                stringBuilder = result;
            }
        }
        return stringBuilder;
    }

    /**
     *以i为中心，向两边偏移，直到不同字母，确定边界k1,k2
     *例如： baaaac aaa,解决中心多个字母重复问题
     * @param i
     * @param str
     * @return
     */
    private static String search(int i, String str) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(str.charAt(i));
        int k1, k2;
        k1=i-1;
        k2=i+1;
        while(k1>=0){
            if(str.charAt(k1) == str.charAt(i)){
                stringBuilder.insert(0, str.charAt(k1));
                k1--;
            }else {
                break;
            }
        }
        while(k2<str.length()){
            if(str.charAt(k2) == str.charAt(i)){
                stringBuilder.append(str.charAt(k2));
                k2++;
            }else {
                break;
            }
        }
        while (k1 >= 0 && k2 < str.length()) {
            if (str.charAt(k1) == str.charAt(k2)) {
                stringBuilder.insert(0, str.charAt(k1));
                stringBuilder.append(str.charAt(k2));
                k1--;
                k2++;
            }else{
                break;
            }

        }
        return stringBuilder.toString();
    }
}
