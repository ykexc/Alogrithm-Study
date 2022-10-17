package leetcode;

import java.util.Arrays;

/**
 * @author 86152
 */
public class LeetCode279 {

    public int numSquares(int n) {

        var dp = new int[n + 1];
        Arrays.fill(dp, 0x3f3f3f3f);
        dp[0] = 0;
        for (int i = 1; i * i <= n; i++) {
            int t = i * i;
            for (int j = t; j <= n; j++) {
                dp[j] = Math.min(dp[j], dp[j - t] + 1);
            }
        }
        return dp[n];
    }


}
