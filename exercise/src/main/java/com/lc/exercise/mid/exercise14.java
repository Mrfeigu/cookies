package com.lc.exercise.mid;

import java.math.BigDecimal;

public class exercise14 {

    public static int divide(int dividend, int divisor) {
        BigDecimal n = new BigDecimal(dividend);
        BigDecimal m = new BigDecimal(divisor);
        return n.divide(m).intValue();
    }

    public static void main(String[] args) {
        System.out.println(divide(7,3));
    }

}
