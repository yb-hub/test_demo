package com.yb.demo.dp;

/**
 * @author yb
 * @description 爬楼梯
 * @data 2021/11/12
 */
public class ClimbStairs_爬楼梯70 {
    public static void main(String[] args) {
        int n = 10;
        System.out.println(climbStairs(n));
    }

    public static int climbStairs(int n) {
        int[] arr = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            if (i == 1 || i ==2) {
                arr[i] = i;
            }else {
                arr[i] = arr[i - 2] + arr[i - 1];
            }
        }
        return arr[n];
    }
}
