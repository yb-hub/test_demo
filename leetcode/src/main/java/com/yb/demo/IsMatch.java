package com.yb.demo;

/**
 * @author yb
 * @description 正则匹配
 * @data 2021/5/17
 */
public class IsMatch {
    public static void main(String[] args) {
        String s1 = "mississippi";
        String s2 = "*is*is*p*.";
        boolean match = isMatch(s1, s2);
        System.out.println(match);
    }

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
        return memory[sLen][pLen];
    }
}
