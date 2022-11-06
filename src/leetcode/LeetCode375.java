package leetcode;

public class LeetCode375 {

    static int N = 210;
    static int[][] cache = new int[N][N];

    public int getMoneyAmount(int n) {
        return dfs(1, n);
    }

    int dfs(int l, int r) {
        if (l >= r) return 0;
        if (cache[l][r] != 0) return cache[l][r];
        int ans = 0x3f3f3f3f;
        for (int x = l; x <= r; x++) {
            // 当选择的数位 x 时，至少需要 cur 才能猜中数字
            int cur = Math.max(dfs(l, x - 1), dfs(x + 1, r)) + x;
            // 在所有我们可以决策的数值之间取最优
            ans = Math.min(ans, cur);
        }
        cache[l][r] = ans;
        return ans;
    }

    /**
     * 区间DP
     */
    public int getMoneyAmount1(int n) {
        var dp = new int[n + 2][n + 2];
        for (int len = 2; len <= n; len++) {
            for (int l = 1; l + len - 1 <= n; l++) {
                int r = l + len - 1;
                dp[l][r] = 0x3f3f3f3f;
                for (int k = l; k <= r; k++) {
                    int cur = Math.max(dp[l][k - 1], dp[k + 1][r]) + k;
                    dp[l][r] = Math.min(dp[l][r], cur);
                }
            }
        }
        return dp[1][n];
    }
}
