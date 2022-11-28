package leetcode;

/**
 * 枚举判断,序列DP
 */
public class LeetCode522 {

    public int findLUSlength(String[] strs) {
        int max = -1;
        for (int i = 0; i < strs.length; i++) {
            int j = 0;
            for (; j < strs.length; j++) {
                if (i == j) {
                    continue;
                }
                if (isSubstring(strs[j], strs[i])) {
                    break;
                }
            }
            if (j == strs.length) {
                max = Math.max(strs[i].length(), max);
            }
        }
        return max;
    }
    public  boolean isSubstring(String s1, String s2) {
        int j = 0;
        for (int i = 0; i < s1.length() && j < s2.length(); i++) {
            if (s1.charAt(i) == s2.charAt(j)) {
                j++;
            }
        }
        return j == s2.length();
    }

    /**
     * 序列DP解法
     */
    public int findLUSlength1(String[] strs) {
        var n = strs.length;
        var ans = -1;
        for (var i = 0; i < n; i++) {
            var flag = true;
            for (var j = 0; j < n; j++) {
                if (i == j) continue;
                if (check(strs[i], strs[j])) {
                    flag = false;
                    break;
                }
            }
            if (flag)
                ans = Math.max(ans, strs[i].length());
        }
        return ans;
    }

    boolean check(String s, String ss) {
        var n = s.length();
        var m = ss.length();
        if (m < n) return false;
        var dp = new int[n + 1][m + 1];
        for (var i = 1; i <= n; i++) {
            for (var j = 1; j <= m; j++) {
                if (s.charAt(i - 1) == ss.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1] + 1;
                } else {
                    dp[i][j] = Math.max(dp[i][j], dp[i - 1][j]);
                    dp[i][j] = Math.max(dp[i][j], dp[i][j - 1]);
                }
            }
        }
        return dp[n][m] == n;
    }
}
