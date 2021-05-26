package com.yb.demo;

import java.text.Normalizer;
import java.util.concurrent.ForkJoinPool;

/**
 * @author yb
 * @description 正则匹配
 * @data 2021/5/17
 */
public class IsMatch {
    public static void main(String[] args) {
//        String s1 = "mississippi";
//        String s2 = "mis*is*p*.";
        String s1 = "aab";
        String s2 = "c*a*b";
        boolean match = isMatch(s1, s2);
        System.out.println(match);
    }

    /**
     * dp[i][j]代表[0,i]和[0,j]是否匹配
     *
     * @param s
     * @param p
     * @return
     */
    public static boolean isMatch(String s, String p) {
        int sLen = s.length(), pLen = p.length();
        boolean[][] memory = new boolean[sLen+1][pLen+1];
        memory[0][0] = true;
        for(int i = 0; i <= sLen; i++) {
            for(int j = 1; j <= pLen; j++) {
                if(p.charAt(j-1) == '*') {
                    memory[i][j] = memory[i][j-2] || (i > 0 && (s.charAt(i-1) == p.charAt(j-2) ||
                            p.charAt(j-2) == '.') && memory[i-1][j]);
                }else {
                    memory[i][j] = i > 0 && (s.charAt(i-1) == p.charAt(j-1) || p.charAt(j-1) == '.')
                            && memory[i-1][j-1];
                }
            }
        }
        printArray(memory,s,p);
        return memory[sLen][pLen];
    }

    private static void printArray(boolean[][] dp, String s, String p) {
        for (int i = 0; i <= s.length(); i++) {
            for (int j = 0; j <= p.length(); j++) {
                if (i == 0 && j == 0) {
                    System.out.print("  ");
                }
                if (i == 0 && j > 0) {
                    System.out.print(p.charAt(j - 1) + " ");
                }
                if (j == 0 && i > 0) {
                    System.out.print(s.charAt(i - 1) + " ");
                }
                if (i > 0 && j > 0) {
                    System.out.print(dp[i - 1][j - 1] ? 1 : 0);
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
}
