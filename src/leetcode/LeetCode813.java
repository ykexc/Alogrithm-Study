package leetcode;

/**
 * 序列DP, 记忆化搜索
 */
public class LeetCode813 {

    public double largestSumOfAverages(int[] nums, int k) {
        var n = nums.length;
        var sum = new double[n + 1];
        for (int i = 0; i < n; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        var dp = new double[n + 1][n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i][1] = sum[i] / i;
        }
        for (int i = 1; i <= n; i++) {
            for (int j = 2; j <= Math.min(i, k); j++) {
                for (int s = 1; s < i; s++) {
                    dp[i][j] = Math.max(dp[i][j], dp[s][j - 1] + (sum[i] - sum[s]) / (i - s));
                }
            }
        }
        return dp[n][k];
    }


    /**
     * 记忆化搜索
     */
    private Double[][] f;
    private double[] s;
    private int n;

    public double largestSumOfAverages2(int[] nums, int k) {
        n = nums.length;
        s = new double[n + 1];
        f = new Double[n + 1][k + 1];
        for (int i = 0; i < n; ++i) {
            s[i + 1] = s[i] + nums[i];
        }
        return dfs(n, k);
    }

    private double dfs(int i, int k) {
        if (i == 0) {
            return 0;
        }
        if (k == 1) {
            return s[i] / i;
        }
        if (f[i][k] != null) {
            return f[i][k];
        }
        double ans = 0;
        for (int j = 1; j < i; ++j) {
            double t = dfs(j, k - 1) + (s[i] - s[j]) / (i - j);
            ans = Math.max(ans, t);
        }
        return f[i][k] = ans;
    }


}
