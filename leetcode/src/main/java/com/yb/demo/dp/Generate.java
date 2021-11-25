package com.yb.demo.dp;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author yb
 * @description 杨辉三角
 * @data 2021/11/12
 */
public class Generate {
    public static void main(String[] args) {
        int n = 4;
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 1; i <=n; i++) {
            generate(i,result);
        }
        System.out.println(result);
    }

    private static void generate(int n,List<List<Integer>> result) {
        if(n == 1){
            List<Integer> list = new ArrayList<>();
            list.add(1);
            result.add(list);
        }else{
            List<Integer> list = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                if(i==0 || i==n-1){
                    list.add(1);
                }else{
                    List<Integer> prve = result.get(n - 2);
                    for (int j = 0; j < prve.size()-1; j++) {
                        list.add(prve.get(j)+prve.get(j+1));
                        if(j < prve.size()-2){
                            i++;
                        }
                    }
                }
            }
            result.add(list);
        }
    }
}
