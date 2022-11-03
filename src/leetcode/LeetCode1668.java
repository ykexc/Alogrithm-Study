package leetcode;

/**
 * 序列DP
 */
public class LeetCode1668 {

    public int maxRepeating(String sequence, String word) {
        int n = sequence.length();
        int m = word.length();
        var ans = 0;
        var dp = new int[n + 10];
        for (var i = 1; i <= n; i++) {
            if (i - m < 0) continue;
            String s = sequence.substring(i - m, i);
            if (s.equals(word)) dp[i] = dp[i - m] + 1;
            ans = Math.max(ans, dp[i]);
        }
        return ans;
    }

}
