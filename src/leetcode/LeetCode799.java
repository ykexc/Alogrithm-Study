package leetcode;

/**
 * 线性DP
 */
public class LeetCode799 {


    public double champagneTower(int k, int n, int m) {
        var dp = new double[n + 5][n + 5];
        dp[0][0] = k;
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if (dp[i][j] <= 1) continue;
                dp[i + 1][j] += (dp[i][j] - 1) / 2;
                dp[i + 1][j + 1] += (dp[i][j] - 1) / 2;
            }
        }
        return Math.min(1.000000, dp[n][m]);
    }
}
