package leetcode;

public class LeetCode790 {

    int mod = (int) 1e9 + 7;
    public int numTilings(int n) {
        var dp = new int[n + 1][4];
        dp[0][3] = 1;
        for (int i = 1; i <= n; i++) {
            dp[i][0] = dp[i - 1][3];
            dp[i][1] = (dp[i - 1][0] + dp[i - 1][2]) % mod;
            dp[i][2] = (dp[i - 1][0] + dp[i - 1][1]) % mod;
            dp[i][3] = ((dp[i - 1][0] + dp[i - 1][1] ) % mod + (dp[i - 1][2] + dp[i - 1][3]) % mod) % mod;
        }
        return dp[n][3];
    }

}
