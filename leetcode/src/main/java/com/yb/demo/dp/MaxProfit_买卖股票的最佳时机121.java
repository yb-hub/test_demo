package com.yb.demo.dp;

/**
 * @Auther: Yang
 * @Date: 2021/12/05 20:52
 * @Description:
 */
public class MaxProfit_买卖股票的最佳时机121 {
    public static void main(String[] args) {
        int[] profits = new int[]{7,1,5,3,6,4};
        int result = maxProfit2(profits);
        System.out.println(result);
    }

    /**
     * 爆破 时间复杂度On2
     * @param profits
     * @return
     */
    private static int maxProfit(int[] profits) {
        int max = 0;
        int start=0;
        int end = 0;
        for (int i = 0; i < profits.length; i++) {
            for (int j = i+1; j < profits.length; j++) {
                if((profits[j]-profits[i]) > max){
                    max = profits[j]-profits[i];
                    start = i;
                    end = j;
                }
            }
        }
        return max;
    }

    /**
     * 记录第i天前的最小值，记录收益最大值
     * 计算当天卖出的最大收益（当天价格-当天前的最小值）
     * 比较收益最大值
     * @param profits
     * @return
     */
    private static int maxProfit2(int[] profits) {
        int max = 0;
        int min = profits[0];
        for (int i = 0; i < profits.length; i++) {
            if(profits.length <= 1){
                return 0;
            }else{
                max = Math.max(max, profits[i]-min);
                min = Math.min(min, profits[i]);
            }
        }
        return max;
    }
}
