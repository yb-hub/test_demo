package com.yb.demo.dp;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Auther: Yang
 * @Date: 2021/12/05 20:02
 * @Description:
 */
public class GetRow_杨辉三角119 {
    public static void main(String[] args) {
        int n = 4;
        List<Integer> result = getRow(n);
        System.out.println(result);
    }

    private static List<Integer> getRow(int n) {
        ArrayList<List<Integer>> lists = new ArrayList<>();
        List<Integer> l1 = Arrays.asList(1);
        List<Integer> l2 = Arrays.asList(1, 1);
        lists.add(l1);
        lists.add(l2);
        //n>2时
        for (int i = 2; i <= n; i++) {
            ArrayList<Integer> tempList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                if(j==0 || j==i){
                    tempList.add(1);
                }else{
                    List<Integer> lastRow = lists.get(i - 1);
                    tempList.add(lastRow.get(j-1)+lastRow.get(j));
                }
            }
            lists.add(tempList);
        }
        return lists.get(n);
    }
}
