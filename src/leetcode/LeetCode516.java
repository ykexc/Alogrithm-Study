package leetcode;

/**
 * 区间DP
 */
public class LeetCode516 {

    public int longestPalindromeSubseq(String s) {
        var n = s.length();
        var dp = new int[n][n];
        for (int i = 0; i < n; i++) dp[i][i] = 1;
        for (int len = 2; len <= n; len++) {
            for (int l = 0; l + len - 1 < n; l++) {
                var r = l + len - 1;
                boolean b1 = s.charAt(l) == s.charAt(r);
                if (len == 2) {
                    dp[l][r] = b1 ? 2 : 1;
                } else {
                    dp[l][r] = Math.max(dp[l][r - 1], dp[l + 1][r]);
                    if (b1) {
                        dp[l][r] = Math.max(dp[l][r], dp[l + 1][r - 1] + 2);
                    }
                }
            }
        }
        return dp[0][n - 1];
    }
}
