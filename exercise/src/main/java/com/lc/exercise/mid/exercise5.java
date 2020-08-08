package com.lc.exercise.mid;

public class exercise5 {



    public static int maxProfit(int[] prices) {
        if(prices.length <= 0 || prices.length >= 100000) return 0;
        // nowMinIndex < nowMaxIndex
        int nowMinIndex = 0; // 表示当前最小价格下标
        int nowMaxIndex = 0;// 表示当前最大价格的下标
        int price = 0; // 最大差价大小
        int index = 0; // 表示差值最大的下标
        int maxPrices = Integer.MIN_VALUE; // 最大价格
        int minPrices = Integer.MAX_VALUE; // 最小价格
        for (int i = 0; i < prices.length; i++) {
            // 找出最小
            if(prices[i] < minPrices){
                // 更新最小价格
                minPrices = prices[i];
                // 更新最小价格下标
                nowMinIndex = i;
            }
            // 找出合格的最大值
            if(prices[i] > minPrices){
                // 更新最大价格下标
                maxPrices = prices[i];
                nowMaxIndex = i;
            }
            // 计算下标差价
            if(price < maxPrices - minPrices && nowMinIndex < nowMaxIndex) {
                price = maxPrices - minPrices;
                index = nowMaxIndex;
            }
        }
        return price;
    }


    public static void main(String[] args) {
        int[] arr = {7,1,5,3,6,4};
        System.out.println(maxProfit(arr));
    }


}
