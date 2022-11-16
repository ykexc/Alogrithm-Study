package leetcode;


/**
 * 区间DP
 */
public class LeetCode664 {


    public int strangePrinter(String s) {
        var n = s.length();
        var dp = new int[n + 1][n + 1];
        for (var i = 0; i < n + 1; i++) dp[i][i] = 1;
        for (var len = 2; len <= n; len++) {
            for (var l = 0; l + len - 1 < n; l++) {
                var r = l + len - 1;
                dp[l][r] = dp[l + 1][r] + 1;
                for (var k = l + 1; k <= r; k++) {
                    if (s.charAt(l) == s.charAt(k)) {
                        dp[l][r] = Math.min(dp[l][r], dp[l][k - 1] + dp[k + 1][r]);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }


}
