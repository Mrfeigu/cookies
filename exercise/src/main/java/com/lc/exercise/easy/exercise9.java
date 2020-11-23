package com.lc.exercise.easy;

/**
 * 二进制求和
 *
 * @author linzhenghui
 * @date 2020/10/20
 */
public class exercise9 {

    public String addBinary(String a, String b) {

        String ar = new StringBuilder(a).reverse().toString();
        String br = new StringBuilder(b).reverse().toString();

        StringBuilder res = new StringBuilder();
        int size = Math.max(ar.length(), br.length());
        int carry = 0;

        int index = 0;
        while(index < ar.length() || index < br.length()) {
            int sum = carry;

            if(index < ar.length()) {
                sum += Integer.parseInt("" + ar.charAt(index));
            }
            if(index < br.length()) {
                sum += Integer.parseInt("" + br.charAt(index));
            }

            if(sum == 0) {
                res.append('0');
                carry = 0;
            } else if(sum == 1) {
                res.append('1');
                carry = 0;
            } else if(sum == 2) {
                res.append('0');
                carry = 1;
            } else if(sum == 3) {
                res.append('1');
                carry = 1;
            }

            index++;
        }

        if(carry == 1) {
            res.append('1');
        }

        return res.reverse().toString();
    }

    public static void main(String[] args) {
        String s = new exercise9().addBinary("1010", "1011");
        System.out.println(s);
        System.out.println("ending...");
    }

}
