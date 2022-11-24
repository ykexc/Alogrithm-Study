package leetcode;

import java.util.Arrays;

public class LeetCode1143 {

    public int longestCommonSubsequence(String a, String b) {
        var n = a.length();
        var m = b.length();
        var dp = new int[n + 1][m + 1];
        a = " " + a;
        b = " " + b;
        var text1 = a.toCharArray();
        var text2 = b.toCharArray();
        Arrays.fill(dp[0], 1);
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 1;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= m; j++) {
                if (text1[i] == text2[j]) {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j - 1] + 1);
                } else dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
            }
        }
        return Math.max(dp[n][m] - 1, 0);
    }

}
