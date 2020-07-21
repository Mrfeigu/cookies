package com.lc.exercise.hard;


public class exercise3 {

    /**
     * 通配符匹配
     * @param s
     * @param p
     * @return
     */
    public boolean isMatch(String s, String p) {
        if("*".equals(p)) return true;
        if("".equals(p) && "".equals(s)) return true;
        if("".equals(p) || "".equals(s)) return false;
        boolean[][] dp = new boolean[p.length() + 1][s.length() + 1];
        // 初始化动态数组
        for (int i = 1; i < p.length() + 1; i++) {
            if(p.charAt(i-1) == '*' && (i == 1 || dp[i-1][0])){
                dp[i][0] = true;
            }
        }
        for (int i = 0; i < s.length() + 1 ; i++) {
            dp[0][i] = true;
        }
        for (int i = 1; i <= p.length(); i++) {
            for (int j = 1; j <= s.length(); j++) {
                if(dp[i-1][j-1]){
                    if(p.charAt(i-1) == '*') {
                        while(j <= s.length()){
                            dp[i][j] = true;
                            j++;
                        }
                        break;
                    } else if(s.charAt(j-1) == p.charAt(i-1) || p.charAt(i-1) == '?'){
                        dp[i][j] = true;
                        if(!(i-2 >= 0 && p.charAt(i-2) == '*')) break;
                    }
                } else{
                    dp[i][j] = false;
                }
            }
        }
        return dp[p.length()][s.length()];
    }

    public static void main(String[] args){
        boolean match = new exercise3().isMatch("abefcdgiescdfimde", "ab*cd?i*de");
        System.out.println("ending...");
    }

}
